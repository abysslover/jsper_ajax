

/* this ALWAYS GENERATED file contains the definitions for the interfaces */


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


/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 440
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif // __RPCNDR_H_VERSION__

#ifndef COM_NO_WINDOWS_H
#include "windows.h"
#include "ole2.h"
#endif /*COM_NO_WINDOWS_H*/

#ifndef __HelloActiveX_h__
#define __HelloActiveX_h__

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
#pragma once
#endif

/* Forward Declarations */ 

#ifndef __IHelloTable_FWD_DEFINED__
#define __IHelloTable_FWD_DEFINED__
typedef interface IHelloTable IHelloTable;
#endif 	/* __IHelloTable_FWD_DEFINED__ */


#ifndef ___IHelloTableEvents_FWD_DEFINED__
#define ___IHelloTableEvents_FWD_DEFINED__
typedef interface _IHelloTableEvents _IHelloTableEvents;
#endif 	/* ___IHelloTableEvents_FWD_DEFINED__ */


#ifndef __HelloTable_FWD_DEFINED__
#define __HelloTable_FWD_DEFINED__

#ifdef __cplusplus
typedef class HelloTable HelloTable;
#else
typedef struct HelloTable HelloTable;
#endif /* __cplusplus */

#endif 	/* __HelloTable_FWD_DEFINED__ */


/* header files for imported files */
#include "oaidl.h"
#include "ocidl.h"

#ifdef __cplusplus
extern "C"{
#endif 

void * __RPC_USER MIDL_user_allocate(size_t);
void __RPC_USER MIDL_user_free( void * ); 

#ifndef __IHelloTable_INTERFACE_DEFINED__
#define __IHelloTable_INTERFACE_DEFINED__

/* interface IHelloTable */
/* [unique][helpstring][nonextensible][dual][uuid][object] */ 


EXTERN_C const IID IID_IHelloTable;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("866B3ACB-FE6A-4D4F-90C6-41167C97355D")
    IHelloTable : public IDispatch
    {
    public:
    };
    
#else 	/* C style interface */

    typedef struct IHelloTableVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            IHelloTable * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            IHelloTable * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            IHelloTable * This);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfoCount )( 
            IHelloTable * This,
            /* [out] */ UINT *pctinfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfo )( 
            IHelloTable * This,
            /* [in] */ UINT iTInfo,
            /* [in] */ LCID lcid,
            /* [out] */ ITypeInfo **ppTInfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetIDsOfNames )( 
            IHelloTable * This,
            /* [in] */ REFIID riid,
            /* [size_is][in] */ LPOLESTR *rgszNames,
            /* [in] */ UINT cNames,
            /* [in] */ LCID lcid,
            /* [size_is][out] */ DISPID *rgDispId);
        
        /* [local] */ HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            IHelloTable * This,
            /* [in] */ DISPID dispIdMember,
            /* [in] */ REFIID riid,
            /* [in] */ LCID lcid,
            /* [in] */ WORD wFlags,
            /* [out][in] */ DISPPARAMS *pDispParams,
            /* [out] */ VARIANT *pVarResult,
            /* [out] */ EXCEPINFO *pExcepInfo,
            /* [out] */ UINT *puArgErr);
        
        END_INTERFACE
    } IHelloTableVtbl;

    interface IHelloTable
    {
        CONST_VTBL struct IHelloTableVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IHelloTable_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define IHelloTable_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define IHelloTable_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define IHelloTable_GetTypeInfoCount(This,pctinfo)	\
    (This)->lpVtbl -> GetTypeInfoCount(This,pctinfo)

#define IHelloTable_GetTypeInfo(This,iTInfo,lcid,ppTInfo)	\
    (This)->lpVtbl -> GetTypeInfo(This,iTInfo,lcid,ppTInfo)

#define IHelloTable_GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)	\
    (This)->lpVtbl -> GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)

#define IHelloTable_Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)	\
    (This)->lpVtbl -> Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)


#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __IHelloTable_INTERFACE_DEFINED__ */



#ifndef __HelloActiveXLib_LIBRARY_DEFINED__
#define __HelloActiveXLib_LIBRARY_DEFINED__

/* library HelloActiveXLib */
/* [helpstring][version][uuid] */ 


EXTERN_C const IID LIBID_HelloActiveXLib;

#ifndef ___IHelloTableEvents_DISPINTERFACE_DEFINED__
#define ___IHelloTableEvents_DISPINTERFACE_DEFINED__

/* dispinterface _IHelloTableEvents */
/* [helpstring][uuid] */ 


EXTERN_C const IID DIID__IHelloTableEvents;

#if defined(__cplusplus) && !defined(CINTERFACE)

    MIDL_INTERFACE("45869562-E514-4F7C-A857-FCA8CB6A108B")
    _IHelloTableEvents : public IDispatch
    {
    };
    
#else 	/* C style interface */

    typedef struct _IHelloTableEventsVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            _IHelloTableEvents * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            _IHelloTableEvents * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            _IHelloTableEvents * This);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfoCount )( 
            _IHelloTableEvents * This,
            /* [out] */ UINT *pctinfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfo )( 
            _IHelloTableEvents * This,
            /* [in] */ UINT iTInfo,
            /* [in] */ LCID lcid,
            /* [out] */ ITypeInfo **ppTInfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetIDsOfNames )( 
            _IHelloTableEvents * This,
            /* [in] */ REFIID riid,
            /* [size_is][in] */ LPOLESTR *rgszNames,
            /* [in] */ UINT cNames,
            /* [in] */ LCID lcid,
            /* [size_is][out] */ DISPID *rgDispId);
        
        /* [local] */ HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            _IHelloTableEvents * This,
            /* [in] */ DISPID dispIdMember,
            /* [in] */ REFIID riid,
            /* [in] */ LCID lcid,
            /* [in] */ WORD wFlags,
            /* [out][in] */ DISPPARAMS *pDispParams,
            /* [out] */ VARIANT *pVarResult,
            /* [out] */ EXCEPINFO *pExcepInfo,
            /* [out] */ UINT *puArgErr);
        
        END_INTERFACE
    } _IHelloTableEventsVtbl;

    interface _IHelloTableEvents
    {
        CONST_VTBL struct _IHelloTableEventsVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define _IHelloTableEvents_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define _IHelloTableEvents_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define _IHelloTableEvents_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define _IHelloTableEvents_GetTypeInfoCount(This,pctinfo)	\
    (This)->lpVtbl -> GetTypeInfoCount(This,pctinfo)

#define _IHelloTableEvents_GetTypeInfo(This,iTInfo,lcid,ppTInfo)	\
    (This)->lpVtbl -> GetTypeInfo(This,iTInfo,lcid,ppTInfo)

#define _IHelloTableEvents_GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)	\
    (This)->lpVtbl -> GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)

#define _IHelloTableEvents_Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)	\
    (This)->lpVtbl -> Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)

#endif /* COBJMACROS */


#endif 	/* C style interface */


#endif 	/* ___IHelloTableEvents_DISPINTERFACE_DEFINED__ */


EXTERN_C const CLSID CLSID_HelloTable;

#ifdef __cplusplus

class DECLSPEC_UUID("BBD89E42-E5DA-4276-8115-0AECA996AD52")
HelloTable;
#endif
#endif /* __HelloActiveXLib_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif


