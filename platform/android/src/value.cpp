#include "value.hpp"

#include "java_types.hpp"
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
        return jni::IsInstanceOf(jenv, value, *java::ObjectArray::jclass);
    }

    bool Value::isObject() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isObject()");
        return jni::IsInstanceOf(jenv, value, *java::Map::jclass);;
    }

    bool Value::isString() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isString()");
        return jni::IsInstanceOf(jenv, value, *java::String::jclass);
    }

    bool Value::isBool() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isBool()");
        return jni::IsInstanceOf(jenv, value, *java::Boolean::jclass);
    }

    bool Value::isNumber() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.isNumber()");
        return jni::IsInstanceOf(jenv, value, *java::Number::jclass);
    }

    std::string Value::toString() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toString()");
        jni::jstring* string = reinterpret_cast<jni::jstring*>(value);
        return jni::Make<std::string>(jenv, jni::String(string));
    }

    float Value::toNumber() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toNumber()");
        return jni::CallMethod<jni::jfloat>(jenv, value, *java::Number::floatValueMethodId);
    }

    bool Value::toBool() const {
        mbgl::Log::Debug(mbgl::Event::JNI, "Value.toBool()");
        return jni::CallMethod<jni::jboolean>(jenv, value, *java::Boolean::booleanValueMethodId);
    }
}
}

