#pragma once

#include <mbgl/util/noncopyable.hpp>
#include <mbgl/style/layer.hpp>

#include "property.hpp"

#include <jni/jni.hpp>

namespace mbgl {
namespace android {

class Layer : private mbgl::util::noncopyable {
public:

    static constexpr auto Name() { return "com/mapbox/mapboxsdk/style/layers/Layer"; };

    /**
     * Register the native class
     */
    static void registerNative(jni::JNIEnv&);

    /*
     * Called when a Java object is already created
     */
    Layer(mbgl::style::Layer&);

    /*
     * Called when a Java object needs to be created
     */
    Layer(JNIEnv& env);

    virtual ~Layer();

    void setProperty(jni::JNIEnv&, jni::jlong jNativeMapPtr, jni::Object<Property> property);

    static jni::Class<Layer> javaClass;

private:
    std::unique_ptr<mbgl::style::Layer> ownedLayer;
    mbgl::style::Layer& layer;

};

} //android
} //mbgl




