#include "property.hpp"

#include <mbgl/platform/log.hpp>

//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

namespace mbgl {
namespace android {

    Property::Property(jni::JNIEnv& env) {

    }


    Property::~Property() {

    }

    jni::Class<Property> Property::javaClass;

    void Property::registerNative(jni::JNIEnv& env) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Registering native property");

        Property::javaClass = *jni::Class<Property>::Find(env).NewGlobalRef(env).release();

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        jni::RegisterNativePeer<Property>(env, Property::javaClass, "nativePtr",
            std::make_unique<Property, JNIEnv&>,
            "initialize",
            "finalize");
    }
}
}