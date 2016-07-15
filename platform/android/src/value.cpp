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
        return false;
    }
}
}

