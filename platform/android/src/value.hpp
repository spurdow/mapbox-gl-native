#pragma once

#include <jni/jni.hpp>

namespace mbgl {
namespace android {

class Value  {
public:

    Value(jni::JNIEnv&, jni::jobject*);
    virtual ~Value();

    bool isNull() const;
    bool isArray() const;
    bool isObject() const;

    jni::JNIEnv& jenv;
    jni::jobject* value;
};

}
}
