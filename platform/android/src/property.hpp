#pragma once

#include <mbgl/util/noncopyable.hpp>

#include <jni/jni.hpp>

namespace mbgl {
namespace android {

class Property : private mbgl::util::noncopyable  {
public:
    static constexpr auto Name() { return "com/mapbox/mapboxsdk/style/layers/Property"; };

    static void registerNative(jni::JNIEnv& env);

    static jni::Class<Property> javaClass;

    Property(jni::JNIEnv& env);

    virtual ~Property();

};

}
}