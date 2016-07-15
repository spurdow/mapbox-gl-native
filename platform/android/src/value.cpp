#include "value.hpp"

#include <mbgl/platform/log.hpp>

//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

namespace mbgl {
namespace android {

    //Instance

    Value::Value(jni::JNIEnv& env, jni::jobject* _value) : jenv(env), value(_value) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value constructed");
    }


    Value::~Value() {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value destroyed");
    }

    bool Value::isNull() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isNull()");
        return value == nullptr;
    }

    bool Value::isArray() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isArray()");
        return false; //TODO dynamic_cast<jni::jarray>(value) != nullptr;
    }

    bool Value::isObject() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isObject()");
        //TODO, not supported yet
        return false;
    }

    bool Value::isString() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isString()");
        static jni::jclass* String = jni::NewGlobalRef(jenv, &jni::FindClass(jenv, "java/lang/String")).release();
        return jni::IsInstanceOf(jenv, value, *String);
    }

    bool Value::isBool() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isBool()");
        static jni::jclass* Boolean = jni::NewGlobalRef(jenv, &jni::FindClass(jenv, "java/lang/Boolean")).release();
        return jni::IsInstanceOf(jenv, value, *Boolean);
    }

    bool Value::isNumber() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isNumber()");
        static jni::jclass* Number = jni::NewGlobalRef(jenv, &jni::FindClass(jenv, "java/lang/Number")).release();
        return jni::IsInstanceOf(jenv, value, *Number);
    }

    std::string Value::toString() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toString()");
        jni::jstring* string = reinterpret_cast<jni::jstring*>(value);
        return jni::Make<std::string>(jenv, jni::String(string));
    }

    float Value::toNumber() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toNumber()");
        //TODO
        return 0.0;
    }

    bool Value::toBool() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toBool()");
        //TODO
        return false;
    }
}
}

