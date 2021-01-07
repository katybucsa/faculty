USE [Primarie]
GO
/****** Object:  StoredProcedure [dbo].[CRUDInteractiuni]    Script Date: 16/12/2018 19:15:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[CRUDInteractiuni] @NrRows INT 
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @k INT
	SELECT @k= dbo.validare(@NrRows)
	IF @k=1
		PRINT 'Parametru de intrare invalid!'
	ELSE
		BEGIN
			--insert
			DECLARE @Aid INT,@Lid INT
			SELECT @Aid=MAX(Aid) FROM Angajati
			SELECT @Lid=MAX(Lid) FROM LocuitorI
			WHILE @NrRows >0
				BEGIN
					INSERT INTO Interactiuni VALUES(@Aid,@Lid,'Descriere')
					SET @Aid=@Aid-1
					SET @Lid=@Lid-1
					SET @NrRows=@NrRows-1
				END
			--select
			SELECT * FROM Interactiuni
			--update
			UPDATE  Interactiuni SET Descriere='Descriere schimbata' WHERE  Descriere='Descriere'
			--delete 
			DELETE FROM Interactiuni WHERE Aid>@Aid	AND Lid>@Lid
			
			PRINT 'Au fost executate operatiile CRUD pe tabelul Interactiuni'
		END
END