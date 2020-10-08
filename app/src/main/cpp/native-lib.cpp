#include <jni.h>
#include <string>
#include "../jni/3rdparty/ffmpeg/include/libavformat/avformat.h"
#include "../jni/3rdparty/ffmpeg/include/libswscale/swscale.h"
#include "../jni/3rdparty/ffmpeg/include/libswresample/swresample.h"
#include "../jni/3rdparty/ffmpeg/include/libavutil/pixdesc.h"
#include "../jni/3rdparty/ffmpeg/include/libavcodec/avcodec.h"

extern "C" JNIEXPORT void JNICALL
Java_com_ws_hugs_common_jni_Mp3Encoder_encodeMp3(JNIEnv *env, jobject thiz) {
    // TODO: implement encodeMp3()

}


extern "C"
JNIEXPORT void JNICALL
Java_com_ws_hugs_common_jni_Mp3Encoder_encodeMp4(JNIEnv *env, jobject thiz, jstring path) {
    // TODO: implement encodeMp4()
    avformat_network_init();
    avcodec_register_all();


    AVFormatContext * formatContext =   avformat_alloc_context();

    AVIOInterruptCB int_cb = {
            interrupt_callback,(_bridge void *)(self)
    };
    formatContext->interrupt_callback = int_cb;
    avformat_open_input(formatContext,path,NULL,NULL);
    avformat_find_stream_info(formatContext,NULL);
}