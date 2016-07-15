export BUILDTYPE ?= Debug

ifeq ($(BUILDTYPE), Release)
else ifeq ($(BUILDTYPE), Debug)
else
  $(error BUILDTYPE must be Debug or Release)
endif

ifeq ($(shell uname -s), Darwin)
  HOST_PLATFORM = macos
  HOST_PLATFORM_VERSION = $(shell uname -m)
  export JOBS ?= $(shell sysctl -n hw.ncpu)
else ifeq ($(shell uname -s), Linux)
  HOST_PLATFORM = linux
  HOST_PLATFORM_VERSION = $(shell uname -m)
  export JOBS ?= $(shell grep --count processor /proc/cpuinfo)
else
  $(error Cannot determine host platform)
endif

ifeq ($(MASON_PLATFORM),)
  BUILD_PLATFORM = $(HOST_PLATFORM)
else
  BUILD_PLATFORM = $(MASON_PLATFORM)
endif

ifeq ($(MASON_PLATFORM_VERSION),)
  BUILD_PLATFORM_VERSION = $(HOST_PLATFORM_VERSION)
else
  BUILD_PLATFORM_VERSION = $(MASON_PLATFORM_VERSION)
endif

ifeq ($(V), 1)
  export XCPRETTY
else
  export XCPRETTY ?= | xcpretty
endif

.PHONY: default
default: test-$(BUILD_PLATFORM)

ifneq (,$(wildcard .git/.))
.mason/mason:
	git submodule update --init
else
.mason/mason: ;
endif

.NOTPARALLEL: node_modules
node_modules: package.json
	npm update # Install dependencies but don't run our own install script.

BUILD_DEPS += .mason/mason
BUILD_DEPS += Makefile
BUILD_DEPS += node_modules

#### macOS targets ##############################################################

ifeq ($(HOST_PLATFORM), macos)

export PATH := $(shell pwd)/platform/macos:$(PATH)

MACOS_OUTPUT_PATH = build/macos
MACOS_PROJ_PATH = $(MACOS_OUTPUT_PATH)/mbgl.xcodeproj
MACOS_WORK_PATH = platform/macos/macos.xcworkspace
MACOS_USER_DATA_PATH = $(MACOS_WORK_PATH)/xcuserdata/$(USER).xcuserdatad
MACOS_COMPDB_PATH = $(MACOS_OUTPUT_PATH)/compdb/$(BUILDTYPE)

MACOS_XCODEBUILD = xcodebuild \
	  -derivedDataPath $(MACOS_OUTPUT_PATH) \
	  -configuration $(BUILDTYPE) \
	  -workspace $(MACOS_WORK_PATH)


MACOS_XCSCHEMES += platform/macos/scripts/executable.xcscheme
MACOS_XCSCHEMES += platform/macos/scripts/library.xcscheme
MACOS_XCSCHEMES += platform/macos/scripts/node.xcscheme

$(MACOS_PROJ_PATH): $(BUILD_DEPS) $(MACOS_USER_DATA_PATH)/WorkspaceSettings.xcsettings $(MACOS_XCSCHEMES)
	mkdir -p $(MACOS_OUTPUT_PATH)
	(cd $(MACOS_OUTPUT_PATH) && cmake -G Xcode ../..)

	@# Create Xcode schemes so that we can use xcodebuild from the command line. CMake doesn't
	@# create these automatically.
	SCHEME_NAME=mbgl-test SCHEME_TYPE=executable platform/macos/scripts/create_scheme.sh
	SCHEME_NAME=mbgl-render SCHEME_TYPE=executable platform/macos/scripts/create_scheme.sh
	SCHEME_NAME=mbgl-offline SCHEME_TYPE=executable platform/macos/scripts/create_scheme.sh
	SCHEME_NAME=mbgl-glfw SCHEME_TYPE=executable platform/macos/scripts/create_scheme.sh
	SCHEME_NAME=mbgl-core SCHEME_TYPE=library BUILDABLE_NAME=libmbgl-core.a BLUEPRINT_NAME=mbgl-core platform/macos/scripts/create_scheme.sh
	SCHEME_NAME=mbgl-node SCHEME_TYPE=library BUILDABLE_NAME=mbgl-node.node BLUEPRINT_NAME=mbgl-node platform/macos/scripts/create_scheme.sh

	@# Create schemes for running node tests. These have all of the environment variables set to
	@# launch in the correct location.
	SCHEME_NAME="node tests" SCHEME_TYPE=node BUILDABLE_NAME=mbgl-node.node BLUEPRINT_NAME=mbgl-node NODE_ARGUMENT="`npm bin tape`/tape platform/node/test/js/**/*.test.js" platform/macos/scripts/create_scheme.sh
	SCHEME_NAME="node render tests" SCHEME_TYPE=node BUILDABLE_NAME=mbgl-node.node BLUEPRINT_NAME=mbgl-node NODE_ARGUMENT="platform/node/test/render.test.js" platform/macos/scripts/create_scheme.sh
	SCHEME_NAME="node query tests" SCHEME_TYPE=node BUILDABLE_NAME=mbgl-node.node BLUEPRINT_NAME=mbgl-node NODE_ARGUMENT="platform/node/test/query.test.js" platform/macos/scripts/create_scheme.sh

$(MACOS_USER_DATA_PATH)/WorkspaceSettings.xcsettings: platform/macos/WorkspaceSettings.xcsettings
	mkdir -p "$(MACOS_USER_DATA_PATH)"
	cp platform/macos/WorkspaceSettings.xcsettings "$@"

.PHONY: macos
macos: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'CI' build $(XCPRETTY)

.PHONY: xproj
xproj: $(MACOS_PROJ_PATH)
	open $(MACOS_WORK_PATH)

.PHONY: test-macos
test-macos: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'mbgl-test' build $(XCPRETTY)
	ulimit -c unlimited && ($(MACOS_OUTPUT_PATH)/$(BUILDTYPE)/mbgl-test & pid=$$! && wait $$pid \
	  || (lldb -c /cores/core.$$pid --batch --one-line 'thread backtrace all' --one-line 'quit' && exit 1))
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'CI' test $(XCPRETTY)

.PHONY: glfw-app
glfw-app: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'mbgl-glfw' build $(XCPRETTY)
	"$(MACOS_OUTPUT_PATH)/$(BUILDTYPE)/mbgl-glfw"

.PHONY: render
render: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'mbgl-render' build $(XCPRETTY)

.PHONY: offline
offline: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'mbgl-offline' build $(XCPRETTY)

.PHONY: node
node: $(MACOS_PROJ_PATH)
	set -o pipefail && $(MACOS_XCODEBUILD) -scheme 'mbgl-node' build $(XCPRETTY)

.PHONY: xpackage
xpackage: $(MACOS_PROJ_PATH)
	SYMBOLS=$(SYMBOLS) ./platform/macos/scripts/package.sh

.PHONY: xdocument
xdocument:
	OUTPUT=$(OUTPUT) ./platform/macos/scripts/document.sh

.PHONY: genstrings
genstrings:
	genstrings -u -o platform/macos/sdk/Base.lproj platform/darwin/src/*.{m,mm}
	genstrings -u -o platform/macos/sdk/Base.lproj platform/macos/src/*.{m,mm}
	genstrings -u -o platform/ios/resources/Base.lproj platform/ios/src/*.{m,mm}
	-find platform/ios/resources platform/macos/sdk -path '*/Base.lproj/*.strings' -exec \
		textutil -convert txt -extension strings -inputencoding UTF-16 -encoding UTF-8 {} \;
	mv platform/macos/sdk/Base.lproj/Foundation.strings platform/darwin/resources/Base.lproj/

$(MACOS_COMPDB_PATH)/Makefile:
	mkdir -p $(MACOS_COMPDB_PATH)
	(cd $(MACOS_COMPDB_PATH) && cmake ../../../.. \
		-DCMAKE_BUILD_TYPE=$(BUILDTYPE) \
		-DCMAKE_EXPORT_COMPILE_COMMANDS=ON)

.PHONY:
compdb: $(BUILD_DEPS) $(TEST_DEPS) $(MACOS_COMPDB_PATH)/Makefile
	@$(MAKE) -C $(MACOS_COMPDB_PATH) cmake_check_build_system

.PHONY: clang-tools
clang-tools: compdb
	if test -z $(CLANG_TIDY); then .mason/mason install clang-tidy 3.8.0; fi
	if test -z $(CLANG_FORMAT); then .mason/mason install clang-format 3.8.0; fi
	$(MAKE) -C $(MACOS_COMPDB_PATH) mbgl-headers

.PHONY: tidy
tidy: clang-tools
	scripts/clang-tools.sh $(MACOS_OUTPUT_PATH)/$(BUILDTYPE)

.PHONY: check
check: clang-tools
	scripts/clang-tools.sh $(MACOS_OUTPUT_PATH)/$(BUILDTYPE) --diff

endif

#### iOS targets ##############################################################

ifeq ($(HOST_PLATFORM), macos)

IOS_OUTPUT_PATH = build/ios
IOS_PROJ_PATH = $(IOS_OUTPUT_PATH)/mbgl.xcodeproj
IOS_WORK_PATH = platform/ios/ios.xcworkspace
IOS_USER_DATA_PATH = $(IOS_WORK_PATH)/xcuserdata/$(USER).xcuserdatad

IOS_XCODEBUILD_SIM = xcodebuild \
	  ARCHS=x86_64 ONLY_ACTIVE_ARCH=YES \
	  -derivedDataPath $(IOS_OUTPUT_PATH) \
	  -configuration $(BUILDTYPE) -sdk iphonesimulator \
	  -destination 'platform=iOS Simulator,name=iPhone 6,OS=latest' \
	  -workspace $(IOS_WORK_PATH)

$(IOS_PROJ_PATH): $(IOS_USER_DATA_PATH)/WorkspaceSettings.xcsettings $(BUILD_DEPS)
	mkdir -p $(IOS_OUTPUT_PATH)
	(cd $(IOS_OUTPUT_PATH) && cmake -G Xcode ../.. \
		-DCMAKE_TOOLCHAIN_FILE=../../platform/ios/toolchain.cmake \
		-DMBGL_PLATFORM=ios)

$(IOS_USER_DATA_PATH)/WorkspaceSettings.xcsettings: platform/ios/WorkspaceSettings.xcsettings
	mkdir -p "$(IOS_USER_DATA_PATH)"
	cp platform/ios/WorkspaceSettings.xcsettings "$@"

.PHONY: ios
ios: $(IOS_PROJ_PATH)
	set -o pipefail && $(IOS_XCODEBUILD_SIM) -scheme 'CI' build $(XCPRETTY)

.PHONY: iproj
iproj: $(IOS_PROJ_PATH)
	open $(IOS_WORK_PATH)

.PHONY: test-ios
test-ios: $(IOS_PROJ_PATH)
	set -o pipefail && $(IOS_XCODEBUILD_SIM) -scheme 'CI' test $(XCPRETTY)

.PHONY: ipackage
ipackage: $(IOS_PROJ_PATH)
	FORMAT=$(FORMAT) BUILD_DEVICE=$(BUILD_DEVICE) SYMBOLS=$(SYMBOLS) \
	./platform/ios/scripts/package.sh

.PHONY: ipackage-strip
ipackage-strip: $(IOS_PROJ_PATH)
	FORMAT=$(FORMAT) BUILD_DEVICE=$(BUILD_DEVICE) SYMBOLS=NO \
	./platform/ios/scripts/package.sh

.PHONY: ipackage-sim
ipackage-sim: $(IOS_PROJ_PATH)
	BUILDTYPE=Debug FORMAT=dynamic BUILD_DEVICE=false SYMBOLS=$(SYMBOLS) \
	./platform/ios/scripts/package.sh

.PHONY: iframework
iframework: $(IOS_PROJ_PATH)
	FORMAT=dynamic BUILD_DEVICE=$(BUILD_DEVICE) SYMBOLS=$(SYMBOLS) \
	./platform/ios/scripts/package.sh

.PHONY: ifabric
ifabric: $(IOS_PROJ_PATH)
	FORMAT=static BUILD_DEVICE=$(BUILD_DEVICE) SYMBOLS=NO SELF_CONTAINED=YES \
	./platform/ios/scripts/package.sh

.PHONY: idocument
idocument:
	OUTPUT=$(OUTPUT) ./platform/ios/scripts/document.sh

endif

#### Linux targets #####################################################

ifeq ($(HOST_PLATFORM), linux)

export PATH := $(shell pwd)/platform/linux:$(PATH)
export LINUX_OUTPUT_PATH = build/linux-$(shell uname -m)/$(BUILDTYPE)
LINUX_BUILD = $(LINUX_OUTPUT_PATH)/build.ninja
NINJA = platform/linux/ninja

$(LINUX_BUILD): $(BUILD_DEPS)
	mkdir -p $(LINUX_OUTPUT_PATH)
	(cd $(LINUX_OUTPUT_PATH) && cmake -G Ninja ../../.. \
		-DCMAKE_BUILD_TYPE=$(BUILDTYPE) \
		-DCMAKE_EXPORT_COMPILE_COMMANDS=ON)

.PHONY: linux
linux: glfw-app render offline

test-linux: run-test-*

.PHONY: linux-render
render: $(LINUX_BUILD)
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-render

.PHONY: linux-offline
offline: $(LINUX_BUILD)
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-offline

.PHONY: glfw-app
glfw-app: $(LINUX_BUILD)
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-glfw

.PHONY: test
test: $(LINUX_BUILD)
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-test

.PHONY: run-glfw-app
run-glfw-app: glfw-app
	cd $(LINUX_OUTPUT_PATH) && ./mbgl-glfw

.PHONY: node
node: $(LINUX_BUILD)
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-node

ifneq (,$(shell which gdb))
  GDB = gdb -batch -return-child-result -ex 'set print thread-events off' -ex 'run' -ex 'thread apply all bt' --args
endif

run-test-%: test
	$(GDB) $(LINUX_OUTPUT_PATH)/mbgl-test --gtest_catch_exceptions=0 --gtest_filter=$*

.PHONY: coverage
coverage: test
	scripts/collect-coverage.sh $(LINUX_OUTPUT_PATH)

.PHONY: compdb
compdb: $(LINUX_BUILD)
	# Ninja generator already outputs the file at the right location

.PHONY: tidy
tidy: compdb
	$(NINJA) -j$(JOBS) -C $(LINUX_OUTPUT_PATH) mbgl-headers
	scripts/clang-tidy.sh $(LINUX_OUTPUT_PATH)

.PHONY: clang-tools
clang-tools:
	if test -z $(CLANG_TIDY); then .mason/mason install clang-tidy 3.8.0; fi
	if test -z $(CLANG_FORMAT); then .mason/mason install clang-format 3.8.0; fi
	deps/ninja/ninja-linux -C $(LINUX_OUTPUT_PATH) headers

.PHONY: tidy
tidy: clang-tools
	scripts/clang-tools.sh $(LINUX_OUTPUT_PATH)

.PHONY: check
check: compdb clang-tools
	scripts/clang-tools.sh $(LINUX_OUTPUT_PATH) --diff

endif

#### Node targets ##############################################################

.PHONY: test-node
test-node: node
	npm test
	npm run test-suite

#### Miscellaneous targets #####################################################

style-code:
	node scripts/generate-style-code.js

.PHONY: clean
clean:
	-rm -rf ./build \
	        ./platform/android/MapboxGLAndroidSDK/build \
	        ./platform/android/MapboxGLAndroidSDKTestApp/build \
	        ./platform/android/MapboxGLAndroidSDK/src/main/jniLibs \
	        ./platform/android/MapboxGLAndroidSDKTestApp/src/main/jniLibs \
	        ./platform/android/MapboxGLAndroidSDK/src/main/assets

.PHONY: distclean
distclean: clean
	-rm -rf ./mason_packages
	-rm -rf ./node_modules
