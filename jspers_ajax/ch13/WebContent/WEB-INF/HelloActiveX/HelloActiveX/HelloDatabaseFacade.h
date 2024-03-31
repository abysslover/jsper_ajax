#pragma once
#include "atlcoll.h"
#pragma warning(push)
#pragma warning(disable:4146)
#import "msado15.dll" no_namespace rename("EOF", "EndOfFile")
#pragma warning(pop)

class HelloDatabaseFacade
{
public:
	HelloDatabaseFacade(CString conStr);
	~HelloDatabaseFacade(void);
	CArray<CArray<CString>*>* execute(CString sql);
	CArray<CArray<CString>*>* doExecute(CString sql);
	void executeUpdate(CString sql);
	void doExecuteUpdate(CString sql);
	CArray<CString>* getColumnNames();
	void makeColumnNames();
private:
	bool connect(ExecuteOptionEnum option = adOptionUnspecified);
	bool close();
	void releaseResultArray();
	CString connectionString;
	int colSize;
	int rowSize;
	_ConnectionPtr	con;
	_RecordsetPtr	rs;
	CArray<CArray<CString>*>* result;
	CArray<CString>* columnNames;
};
