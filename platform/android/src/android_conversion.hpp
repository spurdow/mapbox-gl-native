#pragma once

#include "value.hpp"

#include <mbgl/platform/log.hpp>
#include <mbgl/style/conversion.hpp>
#include <mbgl/util/feature.hpp>
#include <mbgl/util/optional.hpp>

#include <jni/jni.hpp>

namespace mbgl {
namespace style {
namespace conversion {


//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

using namespace mbgl;

inline bool isUndefined(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "isUndefined");
    return value.isNull();
}

inline bool isArray(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "isArray");
    return value.isArray();
}

inline bool isObject(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "isObject");
    return value.isObject();
}

inline std::size_t arrayLength(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "arrayLength");
    //TODO
    return 0;
}

/* TODO return Value */
inline android::Value arrayMember(const android::Value& value, std::size_t i) {
    Log::Debug(mbgl::Event::Android, "arrayMember");
    //TODO
    android::Value newVal(value.jenv);
    return newVal;
}

inline optional<android::Value> objectMember(const android::Value& value, const char* key) {
    Log::Debug(mbgl::Event::Android, "objectMember");
    //TODO
    return {};
}

template <class Fn>
optional<Error> eachMember(const android::Value& value, Fn&& fn) {
    Log::Debug(mbgl::Event::Android, "eachMember");
    //TODO
    return {};
}

inline optional<bool> toBool(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "toBool");
    //TODO
    return {};
}

inline optional<float> toNumber(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "toNumber");
    //TODO
     return 0;
}

inline optional<std::string> toString(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "toString");
    //TODO
    return {};
}

inline optional<android::Value> toValue(const android::Value& value) {
    Log::Debug(mbgl::Event::Android, "toValue");
    //TODO
    return {};
}

} // namespace conversion
} // namespace style
} // namespace mbgl