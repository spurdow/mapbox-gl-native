#include "layer.hpp"
#include <jni/jni.hpp>

//XXX
#pragma GCC diagnostic ignored "-Wunused-parameter"

namespace mbgl {
namespace android {

    Layer::Layer(jni::JNIEnv& env) {
        jni::UniqueLocalFrame frame = jni::PushLocalFrame(env, 10);

        static auto constructor = javaClass.GetConstructor<jni::jlong>(env);

        javaLayer = javaClass.New(env, constructor,
            reinterpret_cast<jlong>(this)).NewGlobalRef(env);
    }

//    Layer::Layer(JNIEnv *env) {
//
//    }

    Layer::~Layer() {

    }

    void Layer::setProperty(jni::JNIEnv& env, jni::jlong jNativeMapPtr, jni::jobject jProperty) {

    }

    jni::Class<Layer> Layer::javaClass;

    void registerNativeLayer(jni::JNIEnv& env) {
        Layer::javaClass = *jni::Class<Layer>::Find(env).NewGlobalRef(env).release();

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        jni::RegisterNativePeer<Layer>(env, Layer::javaClass, "nativePtr",
            METHOD(&Layer::setProperty, "nativeSetProperty"));
    }
}
}