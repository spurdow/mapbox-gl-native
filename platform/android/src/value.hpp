#pragma once

#include <mbgl/util/noncopyable.hpp>
#include <jni/jni.hpp>

namespace mbgl {
namespace android {

//TODO: This doesn't work : private mbgl::util::noncopyable
class Value  {
public:
    static constexpr auto Name() { return "com/mapbox/mapboxsdk/style/layers/Value"; };

    static void registerNative(jni::JNIEnv&);

    static jni::Class<Value> javaClass;

    static Value* instance(jni::JNIEnv&, jni::Object<Value>&);

    Value(jni::JNIEnv&);
    virtual ~Value();

    bool isNull() const;
    bool isArray() const;
    bool isObject() const;

    jni::JNIEnv& jenv;
};

}
}
