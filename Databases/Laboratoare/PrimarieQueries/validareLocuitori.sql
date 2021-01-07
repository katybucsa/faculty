CREATE FUNCTION dbo.validareLocuitori (@Rows INT)
RETURNS BIT
AS 
	BEGIN
		DECLARE @rez BIT=0
		IF @Rows<=0
			SET @rez=1
		RETURN @rez
END
GO

drop function dbo.validareLocuitori