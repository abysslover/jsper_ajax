#include "StdAfx.h"
#include "HelloTableFacade.h"

HelloTableFacade::HelloTableFacade(HWND hWnd) : m_hWnd(hWnd), colSize(0), rowSize(0)
{
	SendMessage(m_hWnd,LVM_SETEXTENDEDLISTVIEWSTYLE,
		0,LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES | LVS_EX_CHECKBOXES |
		LVS_EX_SUBITEMIMAGES);
}

HelloTableFacade::~HelloTableFacade(void)
{
}

int HelloTableFacade::addColumn(CString colName, int width, int columnFormat, int imageNumber) {
	LV_COLUMN		col;
	memset(&col, 0, sizeof(col));
	col.mask		= LVCF_FMT | LVCF_TEXT | LVCF_WIDTH | LVCF_SUBITEM;
	col.cx			= width;
	col.iSubItem	= 0;
	col.pszText		= colName.AllocSysString();
	colName.ReleaseBuffer();
	if (imageNumber != -1){
		col.iImage		= imageNumber;
		col.mask		|= LVCF_IMAGE;
	}
	return ListView_InsertColumn(m_hWnd, colSize++, &col);
}

int HelloTableFacade::addRow(const CArray<CString>& const texts, int imageNumber, LPARAM lParam) {
	LV_ITEM	row;
	memset(&row, 0, sizeof(row));
	row.mask		= LVIF_TEXT | LVIF_PARAM;
	row.iItem		= rowSize;
	row.iSubItem	= 0;
	CString text	= texts.GetAt(0);
	row.pszText		= text.AllocSysString();
	text.ReleaseBuffer();
	if (imageNumber != -1) {
		row.mask	|= LVIF_IMAGE;
		row.iImage	= imageNumber;
	}
	row.lParam		= lParam;
	ListView_InsertItem(m_hWnd, &row);
	for(long col = (long)texts.GetCount()-1; col>0; --col) {
		text = texts.GetAt(col);
		ListView_SetItemText(m_hWnd, rowSize, col, text.AllocSysString());
		text.ReleaseBuffer();
	}
	++rowSize;
	return 0;
}

void HelloTableFacade::resizeHeaders() {
	for(long i=0; i<rowSize; ++i) {
		SendMessage(m_hWnd, LVM_SETCOLUMNWIDTH, i, LVSCW_AUTOSIZE_USEHEADER);
	}
}