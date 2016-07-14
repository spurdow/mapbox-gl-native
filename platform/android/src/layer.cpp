#include "layer.hpp"
#include <jni/jni.hpp>
#include <mbgl/platform/log.hpp>

//XXX
#include <mbgl/style/layers/background_layer.hpp>
#pragma GCC diagnostic ignored "-Wunused-parameter"

namespace mbgl {
namespace android {

    /**
     * Invoked when the construction is initiated from the jvm
     */
    Layer::Layer(jni::JNIEnv& env)
        //TODO: differentiate implementation and use actual parameters (layer id)
        : ownedLayer(std::make_unique<mbgl::style::BackgroundLayer>(*new std::string("layer id")))
        , layer(*ownedLayer) {

        mbgl::Log::Debug(mbgl::Event::JNI, "Layer constructed, owning reference");
    }

    Layer::Layer(mbgl::style::Layer& coreLayer) : layer(coreLayer) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Non-owning reference constructor");
    }

    Layer::~Layer() {
        mbgl::Log::Debug(mbgl::Event::JNI, "Layer destroyed");
    }

    void Layer::setProperty(jni::JNIEnv& env, jni::jlong jNativeMapPtr, jni::Object<Property> property) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Set property");
    }

    jni::Class<Layer> Layer::javaClass;

    void Layer::registerNative(jni::JNIEnv& env) {
        mbgl::Log::Debug(mbgl::Event::JNI, "Registering native layer");

        Layer::javaClass = *jni::Class<Layer>::Find(env).NewGlobalRef(env).release();

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        jni::RegisterNativePeer<Layer>(env, Layer::javaClass, "nativePtr",
            std::make_unique<Layer, JNIEnv&>,
            "initialize",
            "finalize",
            METHOD(&Layer::setProperty, "nativeSetProperty"));
    }
}
}