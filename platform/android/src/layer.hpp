#pragma once

#include <mbgl/util/noncopyable.hpp>
#include <mbgl/style/layer.hpp>

#include <jni/jni.hpp>
#include "attach_env.hpp"

namespace mbgl {
namespace android {

class Layer : private mbgl::util::noncopyable {
public:

    static constexpr auto Name() { return "com/mapbox/mapboxsdk/style/layers/Layer"; };

    /*
     * Called when a Java object is already created
     */
    Layer(mbgl::style::Layer&);

    /*
     * Called when a Java object needs to be created
     */
    Layer(JNIEnv& env);

    virtual ~Layer();

    void setProperty(jni::JNIEnv&, jni::jlong jNativeMapPtr, jni::Object<> jProperty);

    //TODO: Either re-use this or make it private
    static jni::Class<Layer> javaClass;
    jni::UniqueObject<Layer> javaLayer;

private:
    std::unique_ptr<mbgl::style::Layer> ownedLayer;
    mbgl::style::Layer& layer;

};

/**
 * Register the native class
 */
void registerNativeLayer(JNIEnv&);

} //android
} //mbgl




