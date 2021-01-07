CREATE FUNCTION dbo.validareAngajati(@salariu FLOAT,@Rows INT)
RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @k BIT=0
	DECLARE @string VARCHAR(30)
	IF @salariu<=0 
		SET @string=@string+'Salariu invalid!\n'
	IF @Rows<=0
		SET @string=@string+'Numar de linii invalid!'
	IF @string IS NOT NULL
		RETURN @string
	RETURN NULL
END