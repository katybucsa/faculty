--CREATE TABLE Versiune(
--NrVersiune INT)

--SELECT * FROM Versiune

--INSERT INTO Versiune VALUES
--(0);


CREATE PROCEDURE ChangeDBVersion (@vers INT) 
AS
BEGIN
DECLARE @CurrentVersion as INT
DECLARE @Command as VARCHAR(10)
SELECT TOP 1 @CurrentVersion = NrVersiune FROM Versiune
PRINT 'Versiunea curenta este: '+CONVERT(VARCHAR(4),@CurrentVersion)
IF @CurrentVersion < @vers 
BEGIN
	  WHILE @CurrentVersion < @vers
	  BEGIN 
			SET @CurrentVersion = @CurrentVersion + 1
			SET @Command = 'D'+CONVERT(VARCHAR(4),@CurrentVersion) 
			EXEC @Command
	  END
	  UPDATE Versiune
	  SET NrVersiune = @CurrentVersion
	  PRINT 'Noua versiune este: '+CONVERT(VARCHAR(4),@CurrentVersion)
END 
ELSE
BEGIN
	WHILE @CurrentVersion > @vers
	BEGIN
		SET @Command = 'I' + CONVERT(VARCHAR(4),@CurrentVersion)
		EXEC @Command
		SET @CurrentVersion = @CurrentVersion - 1
	END
	UPDATE Versiune
	SET NrVersiune = @CurrentVersion
	PRINT 'Noua versiune este: '+CONVERT(VARCHAR(4),@CurrentVersion)
END
END

EXEC ChangeDBVersion 0