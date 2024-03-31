#pragma once

class HelloTableFacade
{
public:
	HelloTableFacade(HWND hWnd);
	~HelloTableFacade(void);
	int addColumn(CString colName, int width, int columnFormat = 0x0000, int imageNumber = -1);
	int addRow(const CArray<CString>& const texts, int imageNumber = -1, LPARAM lParam = NULL);
	void resizeHeaders();
private:
	int colSize;
	int rowSize;
	HWND m_hWnd;
};
