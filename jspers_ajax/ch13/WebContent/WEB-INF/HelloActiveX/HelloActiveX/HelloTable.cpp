// HelloTable.cpp : CHelloTable의 구현입니다.
#include "stdafx.h"
#include "HelloTable.h"


// CHelloTable

LRESULT CHelloTable::OnInitDialog(UINT uMsg, WPARAM wParam, LPARAM lParam, BOOL& bHandled)
{
	CString conStr;
	conStr.Format(_T("DRIVER={SQL Server};SERVER=%s;UID=%s;PWD=%s;DATABASE=Hello"),
		_T("220.149.36.36,1456"), _T("hello"), _T("magneto"));
	dbFacade = new HelloDatabaseFacade(conStr);
	CArray<CArray<CString>*>* result = dbFacade->execute(_T("hello.GetWriting 10, 10, 1"));

	addListHeaders();
	
	long rows = (long)result->GetCount();
	for(long i=0; i<rows; ++i) {
		CArray<CString>* aCol = result->GetAt(i);
		tableFacade->addRow(*aCol);
	}
	tableFacade->resizeHeaders();
	return TRUE;
}

void CHelloTable::addListHeaders() {
	HWND hTable = GetDlgItem(IDC_TABLE);
	tableFacade = new HelloTableFacade(hTable);
	CArray<CString>* columns = dbFacade->getColumnNames();
	long cols = columns->GetCount();
	for(long i=0; i<cols; ++i) {
		tableFacade->addColumn(columns->GetAt(i), 100);
	}
}
LRESULT CHelloTable::OnDestroy(UINT /*uMsg*/, WPARAM /*wParam*/, LPARAM /*lParam*/, BOOL& /*bHandled*/)
{
	delete tableFacade;
	delete dbFacade;
	return 0;
}
