// HelloTable.h : CHelloTable�� �����Դϴ�.
#pragma once
#include "resource.h"       // �� ��ȣ�Դϴ�.
#include <atlctl.h>
#include "HelloActiveX.h"
#include "_IHelloTableEvents_CP.h"
#include "HelloTableFacade.h"
#include "HelloDatabaseFacade.h"

#if defined(_WIN32_WCE) && !defined(_CE_DCOM) && !defined(_CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA)
#error "���� ������ COM ��ü�� ��ü DCOM ������ �������� �ʴ� Windows Mobile �÷����� ���� Windows CE �÷������� ����� �������� �ʽ��ϴ�. ATL�� ���� ������ COM ��ü�� ������ �����ϰ� ���� ������ COM ��ü ������ ����� �� �ֵ��� _CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA�� �����Ͻʽÿ�. rgs ������ ������ ���� DCOM Windows CE�� �ƴ� �÷������� �����Ǵ� ������ ������ ���̹Ƿ� 'Free'�� �����Ǿ� �ֽ��ϴ�."
#endif


// CHelloTable
class ATL_NO_VTABLE CHelloTable :
	public CComObjectRootEx<CComSingleThreadModel>,
	public IDispatchImpl<IHelloTable, &IID_IHelloTable, &LIBID_HelloActiveXLib, /*wMajor =*/ 1, /*wMinor =*/ 0>,
	public IPersistStreamInitImpl<CHelloTable>,
	public IOleControlImpl<CHelloTable>,
	public IOleObjectImpl<CHelloTable>,
	public IOleInPlaceActiveObjectImpl<CHelloTable>,
	public IViewObjectExImpl<CHelloTable>,
	public IOleInPlaceObjectWindowlessImpl<CHelloTable>,
	public ISupportErrorInfo,
	public IConnectionPointContainerImpl<CHelloTable>,
	public CProxy_IHelloTableEvents<CHelloTable>,
	public IPersistStorageImpl<CHelloTable>,
	public ISpecifyPropertyPagesImpl<CHelloTable>,
	public IQuickActivateImpl<CHelloTable>,
#ifndef _WIN32_WCE
	public IDataObjectImpl<CHelloTable>,
#endif
	public IProvideClassInfo2Impl<&CLSID_HelloTable, &__uuidof(_IHelloTableEvents), &LIBID_HelloActiveXLib>,
#ifdef _WIN32_WCE // ��Ʈ���� ����� �ε��Ϸ��� Windows CE�� IObjectSafety�� �ʿ��մϴ�.
	public IObjectSafetyImpl<CHelloTable, INTERFACESAFE_FOR_UNTRUSTED_CALLER>,
#endif
	public CComCoClass<CHelloTable, &CLSID_HelloTable>,
	public CComCompositeControl<CHelloTable>
{
public:


	CHelloTable()
	{
		m_bWindowOnly = TRUE;
		CalcExtent(m_sizeExtent);
	}

DECLARE_OLEMISC_STATUS(OLEMISC_RECOMPOSEONRESIZE |
	OLEMISC_CANTLINKINSIDE |
	OLEMISC_INSIDEOUT |
	OLEMISC_ACTIVATEWHENVISIBLE |
	OLEMISC_SETCLIENTSITEFIRST
)

DECLARE_REGISTRY_RESOURCEID(IDR_HELLOTABLE)


BEGIN_COM_MAP(CHelloTable)
	COM_INTERFACE_ENTRY(IHelloTable)
	COM_INTERFACE_ENTRY(IDispatch)
	COM_INTERFACE_ENTRY(IViewObjectEx)
	COM_INTERFACE_ENTRY(IViewObject2)
	COM_INTERFACE_ENTRY(IViewObject)
	COM_INTERFACE_ENTRY(IOleInPlaceObjectWindowless)
	COM_INTERFACE_ENTRY(IOleInPlaceObject)
	COM_INTERFACE_ENTRY2(IOleWindow, IOleInPlaceObjectWindowless)
	COM_INTERFACE_ENTRY(IOleInPlaceActiveObject)
	COM_INTERFACE_ENTRY(IOleControl)
	COM_INTERFACE_ENTRY(IOleObject)
	COM_INTERFACE_ENTRY(IPersistStreamInit)
	COM_INTERFACE_ENTRY2(IPersist, IPersistStreamInit)
	COM_INTERFACE_ENTRY(ISupportErrorInfo)
	COM_INTERFACE_ENTRY(IConnectionPointContainer)
	COM_INTERFACE_ENTRY(ISpecifyPropertyPages)
	COM_INTERFACE_ENTRY(IQuickActivate)
	COM_INTERFACE_ENTRY(IPersistStorage)
#ifndef _WIN32_WCE
	COM_INTERFACE_ENTRY(IDataObject)
#endif
	COM_INTERFACE_ENTRY(IProvideClassInfo)
	COM_INTERFACE_ENTRY(IProvideClassInfo2)
#ifdef _WIN32_WCE // ��Ʈ���� ����� �ε��Ϸ��� Windows CE�� IObjectSafety�� �ʿ��մϴ�.
	COM_INTERFACE_ENTRY_IID(IID_IObjectSafety, IObjectSafety)
#endif
END_COM_MAP()

BEGIN_PROP_MAP(CHelloTable)
	PROP_DATA_ENTRY("_cx", m_sizeExtent.cx, VT_UI4)
	PROP_DATA_ENTRY("_cy", m_sizeExtent.cy, VT_UI4)
	// ���� �׸�
	// PROP_ENTRY("�Ӽ� ����", dispid, clsid)
	// PROP_PAGE(CLSID_StockColorPage)
END_PROP_MAP()

BEGIN_CONNECTION_POINT_MAP(CHelloTable)
	CONNECTION_POINT_ENTRY(__uuidof(_IHelloTableEvents))
END_CONNECTION_POINT_MAP()

BEGIN_MSG_MAP(CHelloTable)
	MESSAGE_HANDLER(WM_INITDIALOG, OnInitDialog)
	MESSAGE_HANDLER(WM_DESTROY, OnDestroy)
	CHAIN_MSG_MAP(CComCompositeControl<CHelloTable>)
END_MSG_MAP()
// ó���� ������Ÿ��:
//  LRESULT MessageHandler(UINT uMsg, WPARAM wParam, LPARAM lParam, BOOL& bHandled);
//  LRESULT CommandHandler(WORD wNotifyCode, WORD wID, HWND hWndCtl, BOOL& bHandled);
//  LRESULT NotifyHandler(int idCtrl, LPNMHDR pnmh, BOOL& bHandled);

BEGIN_SINK_MAP(CHelloTable)
	//�̺�Ʈ ó�����  __stdcall ȣ�� ��Ģ�� ����ؾ� �մϴ�.
END_SINK_MAP()

	STDMETHOD(OnAmbientPropertyChange)(DISPID dispid)
	{
		if (dispid == DISPID_AMBIENT_BACKCOLOR)
		{
			SetBackgroundColorFromAmbient();
			FireViewChange();
		}
		return IOleControlImpl<CHelloTable>::OnAmbientPropertyChange(dispid);
	}
// ISupportsErrorInfo
	STDMETHOD(InterfaceSupportsErrorInfo)(REFIID riid)
	{
		static const IID* arr[] =
		{
			&IID_IHelloTable,
		};

		for (int i=0; i<sizeof(arr)/sizeof(arr[0]); i++)
		{
			if (InlineIsEqualGUID(*arr[i], riid))
				return S_OK;
		}
		return S_FALSE;
	}

// IViewObjectEx
	DECLARE_VIEW_STATUS(VIEWSTATUS_SOLIDBKGND | VIEWSTATUS_OPAQUE)

// IHelloTable

	enum { IDD = IDD_HELLOTABLE };

	DECLARE_PROTECT_FINAL_CONSTRUCT()

	HRESULT FinalConstruct()
	{
		return S_OK;
	}

	void FinalRelease()
	{
	}
	LRESULT OnInitDialog(UINT uMsg, WPARAM wParam, LPARAM lParam, BOOL& bHandled);
private:
	HelloTableFacade* tableFacade;
	HelloDatabaseFacade* dbFacade;
	void addListHeaders();
public:
	LRESULT OnDestroy(UINT /*uMsg*/, WPARAM /*wParam*/, LPARAM /*lParam*/, BOOL& /*bHandled*/);
};

OBJECT_ENTRY_AUTO(__uuidof(HelloTable), CHelloTable)
