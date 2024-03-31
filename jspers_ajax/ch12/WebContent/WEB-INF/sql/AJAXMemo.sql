CREATE TABLE AJAXMemo(
	id BIGINT IDENTITY,
	parentId BIGINT,
	writtentime INT DEFAULT DATEDIFF(ss, '19700101', GETDATE()),
	writer VARCHAR(24) NOT NULL,
	content TEXT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(parentId) REFERENCES AJAXBoard(id)
);
go
DROP TABLE AJAXMemo;