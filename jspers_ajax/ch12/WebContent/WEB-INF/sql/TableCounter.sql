CREATE TABLE TableCounter(
	name VARCHAR(50) PRIMARY KEY,
	counts BIGINT DEFAULT 0
);
go
DROP TABLE TableCounter;