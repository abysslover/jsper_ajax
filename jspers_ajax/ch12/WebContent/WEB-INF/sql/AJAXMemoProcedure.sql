-- CRUD for AJAXMemo
-- C(INSERT)
CREATE PROC hello.NewMemo @parentId BIGINT, @writer VARCHAR(24), @content TEXT
	AS
	DECLARE @deleted BIT
	SELECT @deleted = deleted FROM AJAXBoard WHERE id = @parentId
	IF @deleted = 0
	BEGIN
		INSERT INTO AJAXMemo(parentId, writer, content) VALUES (@parentId, @writer, @content)
		UPDATE AJAXBoard SET subcount = subcount + 1 WHERE id = @parentId
	END
go
-- R(SELECT)
CREATE PROC hello.GetMemo @parentId BIGINT
	AS
	SELECT id, dateadd(ss, writtentime, '19700101') AS writtentime, writer, content FROM AJAXMemo
	WHERE parentId = @parentId;
go
-- U(UPDATE)
CREATE PROC hello.UpdateMemo @id BIGINT, @content TEXT
	AS UPDATE AJAXMemo SET content = @content WHERE id = @id;
go
-- D(DELETE)
CREATE PROC hello.DeleteMemo @id BIGINT
	AS
	DECLARE @parentId BIGINT
	SELECT @parentId = parentId FROM AJAXMemo WHERE id = @id
	DELETE AJAXMemo WHERE id = @id
	UPDATE AJAXBoard SET subcount = subcount - 1 WHERE deleted = 0 AND id = @parentId;
go
DROP PROC hello.NewMemo
go
DROP PROC hello.GetMemo
go
DROP PROC hello.UpdateMemo
go
DROP PROC hello.DeleteMemo
go