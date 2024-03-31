// HelloActiveX.cpp : DLL ���������� �����Դϴ�.


#include "stdafx.h"
#include "resource.h"
#include "HelloActiveX.h"


class CHelloActiveXModule : public CAtlDllModuleT< CHelloActiveXModule >
{
public :
	DECLARE_LIBID(LIBID_HelloActiveXLib)
	DECLARE_REGISTRY_APPID_RESOURCEID(IDR_HELLOACTIVEX, "{D571A11A-D63E-4204-A6C4-122EBC71D9BE}")
};

CHelloActiveXModule _AtlModule;

class CHelloActiveXApp : public CWinApp
{
public:

// �������Դϴ�.
    virtual BOOL InitInstance();
    virtual int ExitInstance();

    DECLARE_MESSAGE_MAP()
};

BEGIN_MESSAGE_MAP(CHelloActiveXApp, CWinApp)
END_MESSAGE_MAP()

CHelloActiveXApp theApp;

BOOL CHelloActiveXApp::InitInstance()
{
    return CWinApp::InitInstance();
}

int CHelloActiveXApp::ExitInstance()
{
    return CWinApp::ExitInstance();
}


// DLL�� OLE�� ���� ��ε�� �� �ִ��� �����ϴ� �� ���˴ϴ�.
STDAPI DllCanUnloadNow(void)
{
    AFX_MANAGE_STATE(AfxGetStaticModuleState());
    return (AfxDllCanUnloadNow()==S_OK && _AtlModule.GetLockCount()==0) ? S_OK : S_FALSE;
}


// Ŭ���� ���͸��� ��ȯ�Ͽ� ��û�� ������ ��ü�� ����ϴ�.
STDAPI DllGetClassObject(REFCLSID rclsid, REFIID riid, LPVOID* ppv)
{
    return _AtlModule.DllGetClassObject(rclsid, riid, ppv);
}


// DllRegisterServer - �ý��� ������Ʈ���� �׸��� �߰��մϴ�.
STDAPI DllRegisterServer(void)
{
    // ��ü, ���� ���̺귯�� �� ���� ���̺귯���� ��� �ִ� ��� �������̽��� ����մϴ�.
    HRESULT hr = _AtlModule.DllRegisterServer();
	return hr;
}


// DllUnregisterServer - �ý��� ������Ʈ������ �׸��� �����մϴ�.
STDAPI DllUnregisterServer(void)
{
	HRESULT hr = _AtlModule.DllUnregisterServer();
	return hr;
}

