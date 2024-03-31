-- CRUD for AJAXBoard
-- C(INSERT)
CREATE PROC hello.NewWriting @writer VARCHAR(24), @subject VARCHAR(200), @content text
	AS
	DECLARE @counts BIGINT;
	SELECT @counts = hello.getCount('AJAXBoard');
	INSERT INTO AJAXBoard(id, writer, subject, content) VALUES (@counts, @writer, @subject, @content);
	SET @counts = @counts + 1;
	EXEC hello.UpdateCounter 'AJAXBoard', @counts;
go
-- R(SELECT)
CREATE PROC hello.GetWriting @page INT, @displayRowCount INT, @rowCountInPage INT, @predicate VARCHAR(200) = '1=1'
	AS
	DECLARE @sql NVARCHAR(4000)
	SET @sql = N'SELECT id, dateadd(ss, writtentime, ''19700101'') AS writtentime, writer, subcount, reads, ' +
	' subject FROM (SELECT TOP ' + CAST(@displayRowCount AS VARCHAR(10)) + ' id, writtentime, writer, ' +
	' subcount, reads, subject FROM (SELECT TOP ' + CAST(@page * @rowCountInPage AS VARCHAR(10)) + 
	' id, writtentime, writer, subcount, reads, subject FROM AJAXBoard WHERE deleted=0 AND ' + @predicate +
	' ORDER BY id DESC) AS A ORDER BY id ASC) AS B ORDER BY id DESC'
	EXEC sp_executesql @sql;
go
CREATE PROC hello.GetContent @id BIGINT
	AS
	SELECT dateadd(ss, writtentime, '19700101') AS writtentime, writer, subject, content
	FROM AJAXBoard WHERE id = @id AND deleted = 0
	UPDATE AJAXBoard SET reads = reads + 1 WHERE id = @id AND deleted = 0;
go

-- U(UPDATE)
CREATE PROC hello.UpdateWriting @id BIGINT, @subject VARCHAR(200), @content TEXT
	AS UPDATE AJAXBoard SET subject=@subject, content=@content WHERE id = @id;
-- D(DELETE)
CREATE PROC hello.DeleteWriting @id BIGINT
	AS UPDATE AJAXBoard SET deleted = 1 WHERE id = @id;
-- Rows Count for Search
CREATE PROC hello.GetCountWithPredicate @predicate VARCHAR(200) = '1=1'
	AS
	DECLARE @sql NVARCHAR(4000)
	SET @sql = 'SELECT COUNT(*) FROM AJAXBoard WHERE deleted = 0 AND ' + @predicate
	EXEC sp_executesql @sql;
go
DROP PROC hello.NewWriting;
go
DROP PROC hello.GetWriting;
go
DROP PROC hello.GetContent;
go
DROP PROC hello.UpdateWriting;
go
DROP PROC hello.DeleteWriting;
go
DROP PROC hello.GetCountWithPredicate;
go