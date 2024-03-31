cd workspace\ch01\bin
"E:/Program Files/Microsoft Visual Studio 8/VC/bin/cl" /I "C:\Java\jdk1.6.0_04\include" /I "C:\Java\jdk1.6.0_04\include\win32" /I "E:\Program Files\Microsoft Visual Studio 8\VC\include" com_javawide_book_blackbook1_ch01_image_jni_CxImageWrapper.c /LD /Focomjavawide_CxImageWrapper /link "E:\Program Files\Microsoft Visual Studio 8\VC\lib\*.lib"
copy comjavawide_CxImageWrapper.dll C:\Java\jre1.6.0_04\bin
pause