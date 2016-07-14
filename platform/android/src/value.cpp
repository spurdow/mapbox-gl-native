#include "value.hpp"

#include <mbgl/platform/log.hpp>

//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

namespace mbgl {
namespace android {

    //Static

    jni::Class<Value> Value::javaClass;

    void Value::registerNative(jni::JNIEnv& env) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Registering native Value");

        Value::javaClass = *jni::Class<Value>::Find(env).NewGlobalRef(env).release();

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        jni::RegisterNativePeer<Value>(env, Value::javaClass, "nativePtr",
            std::make_unique<Value, JNIEnv&>,
            "initialize",
            "finalize");
    }

    Value* Value::instance(jni::JNIEnv& env, jni::Object<Value>& obj) {
        static jni::Field<Value, jni::jlong> nativePtrField { env, Value::javaClass, "nativePtr" };
        return reinterpret_cast<Value*>(obj.Get(env, nativePtrField));
    }

    //Instance

    Value::Value(jni::JNIEnv& env) : jenv(env) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value constructed");
    }


    Value::~Value() {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value destroyed");
    }

    bool Value::isNull() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isNull()");
       // static auto method = javaClass.GetMethod<void ()>(*env, "isNull");

        //javaRequest->Call(*env, method)
        return 1;
    }

    bool Value::isArray() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isArray()");
        //TODO
        return false;
    }

    bool Value::isObject() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isObject()");
        return false;
    }
}
}

