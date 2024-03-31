#include "com_javawide_book_blackbook1_ch01_image_jni_CxImageWrapper.h"

char* jstringToWindows(JNIEnv* env, jstring jstr);
JNIEXPORT void JNICALL Java_com_javawide_book_blackbook1_ch01_image_jni_CxImageWrapper_doCrop
  (JNIEnv *env, jobject jobj, jstring srcFileName, jstring dstFileName, jint startX, jint startY, jint width, jint height) {
	const char *cSrcFileName = jstringToWindows(env, srcFileName);
	const char *cDstFileName = jstringToWindows(env, dstFileName);
	printf("Source : %s, Destination : %s", cSrcFileName, cDstFileName);
	CxImage src;
	src.Load(cSrcFileName);
	if(src.IsValid()) {
		src.Crop(startX, startY, width, height);
		if(src.Save(cDstFileName, CXIMAGE_FORMAT_JPG))
			printf("Success");
	}
	env->ReleaseStringUTFChars(srcFileName, cSrcFileName);
	env->ReleaseStringUTFChars(dstFileName, cDstFileName);   
}

JNIEXPORT void JNICALL Java_com_javawide_book_blackbook1_ch01_image_jni_CxImageWrapper_doResample
	(JNIEnv *env, jobject jobj, jstring srcFileName, jstring dstFileName, jint width, jint height) {
	
	const char *cSrcFileName = jstringToWindows(env, srcFileName);
	const char *cDstFileName = jstringToWindows(env, dstFileName);
	CxImage src;
	src.Load(cSrcFileName);
	printf("Source : %s, Destination : %s", cSrcFileName, cDstFileName);
	if(src.IsValid()) {
		src.Resample(width, height);
		if(!src.IsGrayScale()) src.IncreaseBpp(24);
		src.SetJpegQuality(75);
		if(src.Save(cDstFileName, CXIMAGE_FORMAT_JPG))
			printf("Success");			
	}
	env->ReleaseStringUTFChars(srcFileName, cSrcFileName);
	env->ReleaseStringUTFChars(dstFileName, cDstFileName);
}

char* jstringToWindows(JNIEnv* env, jstring jstr) {
	jboolean isCopy = 0;
	int length = env->GetStringLength(jstr);
	const jchar* jcstr = env->GetStringChars(jstr, &isCopy);
	char* rtn = (char*)malloc(length*2+1);
	int size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, rtn, (length*2+1), NULL, NULL);
	if(size <= 0) return NULL;
	env->ReleaseStringChars(jstr, jcstr);
	rtn[size] = 0;
	return rtn;
}