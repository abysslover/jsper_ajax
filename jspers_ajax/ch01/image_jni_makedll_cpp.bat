cd workspace\ch01\bin

ECHO ��� ����
SET VCPATH=D:/Programs/Microsoft Visual Studio 8/VC
SET PLATFORMSDK=%VCPATH%/PlatformSDK/Lib
SET JDKPATH=C:\Java\jdk1.6.0_10

ECHO ������ ��� ����
SET COMMAND=%VCPATH%/bin/cl
SET OPTIONS=/O2 /Ob1 /GF /FD /EHsc /MD /Gy /nologo /TP
SET INPUT_FILENAME=com_javawide_book_blackbook1_ch01_image_jni_CxImageWrapper.cpp
SET OUTPUT_FILENAME=comjavawide_CxImageWrapper

ECHO ������ ����
"%COMMAND%" %OPTIONS% /D "WIN32" /I "%JDKPATH%\include" /I "%JDKPATH%/include/win32" /I "%VCPATH%/include" /I "%VCPATH%/PlatformSDK/Include" %INPUT_FILENAME% /LD /Fo%OUTPUT_FILENAME% /link "%VCPATH%/lib/uuid.lib" "%VCPATH%/lib/msvcrt.lib" "%VCPATH%/lib/oldnames.lib" "%VCPATH%/lib/kernel32.lib" "%PLATFORMSDK%/ws2_32.lib"

ECHO manifest ����
"%VCPATH%/bin/mt" -nologo -manifest %OUTPUT_FILENAME%.dll.manifest -outputresource:%OUTPUT_FILENAME%.dll;2

ECHO ���� ����
copy %OUTPUT_FILENAME%.dll C:\Java\jdk1.6.0_10\jre6\bin

ECHO ���� ���� �Ϸ�
pause