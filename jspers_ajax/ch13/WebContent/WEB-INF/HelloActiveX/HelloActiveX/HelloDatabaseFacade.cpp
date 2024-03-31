#include "StdAfx.h"
#include "HelloDatabaseFacade.h"

HelloDatabaseFacade::HelloDatabaseFacade(CString conStr) : colSize(0), rowSize(0), connectionString(conStr), con(NULL),
rs(NULL), result(NULL) {
}

HelloDatabaseFacade::~HelloDatabaseFacade(void)
{
	close();
}

CArray<CArray<CString>*>* HelloDatabaseFacade::execute(CString sql) {
	CArray<CArray<CString>*>* result;
	try {
		connect();
		result = doExecute(sql);
	} catch (_com_error &e) {
		MessageBox(NULL, e.ErrorMessage(), _T("에러 - execute"), MB_OK);
	}
	close();
	return result;
}

void HelloDatabaseFacade::makeColumnNames() {
	colSize = rs->Fields->GetCount();
	columnNames = new CArray<CString>();
	CString columnName;
	for(long i=0; i<colSize; ++i) {
		columnName = CString(rs->Fields->GetItem(_variant_t(i))->GetName().copy());
		columnNames->Add(columnName);
	}
}

CArray<CArray<CString>*>* HelloDatabaseFacade::doExecute(CString sql) {
	if(con->GetState() != adStateOpen) return FALSE;
	result = new CArray<CArray<CString>*>();
	rowSize = 0;
	_bstr_t aSql = sql.AllocSysString();
	_variant_t data;
	CArray<CString>* aRow;
	rs = con->Execute(aSql, NULL, adOptionUnspecified);
	sql.ReleaseBuffer();

	makeColumnNames();
	while(!rs->GetEndOfFile()) {
		aRow = new CArray<CString>();
		for(long i=0; i<colSize; ++i) {
			CString columnName = getColumnNames()->GetAt(i);
			data = rs->GetCollect(columnName.AllocSysString());
			columnName.ReleaseBuffer();
			aRow->Add(CString(data));
			data.Clear();
		}
		result->Add(aRow);
		rs->MoveNext();
		++rowSize;
	}
	return result;
}
bool HelloDatabaseFacade::connect(ExecuteOptionEnum option) {
	if (NULL != con) return FALSE;
	try {
		con.CreateInstance(__uuidof(Connection));
		rs.CreateInstance(__uuidof(Recordset));
		con->ConnectionTimeout = 10;
		_bstr_t emptyString = _T("");
		con->Open(connectionString.AllocSysString(), emptyString, emptyString, option);
		connectionString.ReleaseBuffer();
	} catch(_com_error &e) {
		MessageBox(NULL, e.ErrorMessage(), _T("에러 - 연결"), MB_OK);
		return FALSE;
	}
	if(NULL != result) {
		releaseResultArray();
	}
	return TRUE;
}

void HelloDatabaseFacade::releaseResultArray() {
	if(NULL != result) return;
	CArray<CString> * aRow;
	for(long i=(long)result->GetCount()-1; i>0; --i) {
		aRow = result->GetAt(i);
		delete aRow;
	}
	delete result;
	delete columnNames;
}

bool HelloDatabaseFacade::close() {
	if (NULL != con) return FALSE;
	releaseResultArray();
	try {
		if(con->GetState()!=adStateClosed) {
			con->Close();
			con.Release();
		}
		if(rs->GetState()!=adStateClosed) {
			rs->Close();
			rs.Release();
		}
		con = NULL;
		rs = NULL;
	} catch(_com_error &e) {
		MessageBox(NULL, e.ErrorMessage(), _T("에러 - 해제"), MB_OK);
		return FALSE;
	}
	return TRUE;
}

CArray<CString>* HelloDatabaseFacade::getColumnNames() {
	return columnNames;
}