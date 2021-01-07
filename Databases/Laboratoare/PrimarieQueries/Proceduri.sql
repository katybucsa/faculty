CREATE PROCEDURE D1 
AS
BEGIN
ALTER TABLE Departamente 
ALTER COLUMN Nume NVARCHAR(200) NOT NULL
PRINT 'Coloana Nume din tabela Departamente a fost modificata cu succes!'
END


CREATE PROCEDURE I1
AS 
BEGIN
ALTER TABLE Departamente
ALTER COLUMN Nume VARCHAR(100) NOT NULL
PRINT 'Coloana Nume din tabela Departamente a fost modificata cu succes!'
END


CREATE PROCEDURE D2
AS
BEGIN
ALTER TABLE Locuitori
ADD CONSTRAINT df_Serviciu DEFAULT 'angajat' FOR Serviciu
PRINT 'Coloana Serviciu din tabela Locuitori a fost modificata cu succes!'
END

CREATE PROCEDURE I2
AS
BEGIN
ALTER TABLE Locuitori
DROP CONSTRAINT df_Serviciu
PRINT 'Coloana Serviciu din tabela Locuitori a fost modificata cu succes!'
END


CREATE PROCEDURE D3
AS
BEGIN
CREATE TABLE Proprietati(
Propid INT PRIMARY KEY,
Tip VARCHAR(50),
Suprafata FLOAT CHECK(Suprafata>0))
PRINT 'Tabela Proprietati a fost creata cu succes!'
END


CREATE PROCEDURE I3
AS
BEGIN
DROP TABLE Proprietati
PRINT 'Tabela Proprietati a fost stearsa cu succes!'
END


CREATE PROCEDURE D4
AS 
BEGIN
ALTER TABLE Proprietati
ADD Zona VARCHAR(30)
PRINT 'Campul Zona a fost adaugat cu succes!'
END

CREATE PROCEDURE I4
AS 
BEGIN
ALTER TABLE Proprietati
DROP COLUMN Zona
PRINT 'Campul Zona a fost sters cu succes!'
END

CREATE PROCEDURE D5
AS
BEGIN
ALTER TABLE Proprietati
ADD CONSTRAINT fk_Proprietati FOREIGN KEY(Lid) REFERENCES Locuitori(Lid)
PRINT 'In tabela Proprietati a fost adaugata o cheie straina!'
END

CREATE PROCEDURE I5
AS
BEGIN
ALTER TABLE Proprietati
DROP CONSTRAINT fk_Proprietati
PRINT 'Cheia straina din tabela Proprietati a fost stearsa cu succes!'
END