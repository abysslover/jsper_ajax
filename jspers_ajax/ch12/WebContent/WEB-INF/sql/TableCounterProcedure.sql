-- CRUD for TableCounter
-- C(INSERT)
CREATE PROC hello.NewCounter @tableName VARCHAR(50)
	AS INSERT INTO TableCounter(name, counts) VALUES (@tableName, 0);
go
-- R(SELECT)
CREATE FUNCTION hello.GetCount(@tableName VARCHAR(50))
RETURNS BIGINT
	AS
BEGIN
	DECLARE @counts BIGINT;
	SELECT @counts = counts FROM TableCounter WHERE name = @tableName;
	RETURN @counts;
END
go
-- U(UPDATE)
CREATE PROC hello.UpdateCounter @tableName VARCHAR(50), @counts BIGINT
	AS UPDATE TableCounter SET counts = @counts WHERE name = @tableName;
go
-- D(DELETE)
CREATE PROC hello.DeleteCounter @tableName VARCHAR(50)
	AS DELETE FROM TableCounter WHERE name = @tableName;
go
DROP FUNCTION hello.NewCounter;
go
DROP FUNCTION hello.GetCount
go
DROP PROC hello.UpdateCounter;
go
DROP PROC hello.DeleteCounter;
go