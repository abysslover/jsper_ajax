

/* this ALWAYS GENERATED file contains the IIDs and CLSIDs */

/* link this file in with the server and any clients */


 /* File created by MIDL compiler version 6.00.0366 */
/* at Sat Mar 22 01:49:43 2008
 */
/* Compiler settings for .\HelloActiveX.idl:
    Oicf, W1, Zp8, env=Win32 (32b run)
    protocol : dce , ms_ext, c_ext
    error checks: allocation ref bounds_check enum stub_data 
    VC __declspec() decoration level: 
         __declspec(uuid()), __declspec(selectany), __declspec(novtable)
         DECLSPEC_UUID(), MIDL_INTERFACE()
*/
//@@MIDL_FILE_HEADING(  )

#pragma warning( disable: 4049 )  /* more than 64k source lines */


#ifdef __cplusplus
extern "C"{
#endif 


#include <rpc.h>
#include <rpcndr.h>

#ifdef _MIDL_USE_GUIDDEF_

#ifndef INITGUID
#define INITGUID
#include <guiddef.h>
#undef INITGUID
#else
#include <guiddef.h>
#endif

#define MIDL_DEFINE_GUID(type,name,l,w1,w2,b1,b2,b3,b4,b5,b6,b7,b8) \
        DEFINE_GUID(name,l,w1,w2,b1,b2,b3,b4,b5,b6,b7,b8)

#else // !_MIDL_USE_GUIDDEF_

#ifndef __IID_DEFINED__
#define __IID_DEFINED__

typedef struct _IID
{
    unsigned long x;
    unsigned short s1;
    unsigned short s2;
    unsigned char  c[8];
} IID;

#endif // __IID_DEFINED__

#ifndef CLSID_DEFINED
#define CLSID_DEFINED
typedef IID CLSID;
#endif // CLSID_DEFINED

#define MIDL_DEFINE_GUID(type,name,l,w1,w2,b1,b2,b3,b4,b5,b6,b7,b8) \
        const type name = {l,w1,w2,{b1,b2,b3,b4,b5,b6,b7,b8}}

#endif !_MIDL_USE_GUIDDEF_

MIDL_DEFINE_GUID(IID, IID_IHelloTable,0x866B3ACB,0xFE6A,0x4D4F,0x90,0xC6,0x41,0x16,0x7C,0x97,0x35,0x5D);


MIDL_DEFINE_GUID(IID, LIBID_HelloActiveXLib,0x8C17399A,0xE67B,0x4A17,0xA3,0x8C,0x35,0x24,0x4A,0x46,0x0C,0xA3);


MIDL_DEFINE_GUID(IID, DIID__IHelloTableEvents,0x45869562,0xE514,0x4F7C,0xA8,0x57,0xFC,0xA8,0xCB,0x6A,0x10,0x8B);


MIDL_DEFINE_GUID(CLSID, CLSID_HelloTable,0xBBD89E42,0xE5DA,0x4276,0x81,0x15,0x0A,0xEC,0xA9,0x96,0xAD,0x52);

#undef MIDL_DEFINE_GUID

#ifdef __cplusplus
}
#endif



