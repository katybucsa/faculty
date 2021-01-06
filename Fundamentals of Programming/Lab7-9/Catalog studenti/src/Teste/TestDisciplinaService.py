
import unittest
from Teste.TestDisciplinaRepository import TestDisciplinaRepository
from Teste.TestDisciplinaValidator import  TestDisciplinaValidator
from Service.DisciplinaService import DisciplinaService
from Domain.Disciplina import Disciplina, Prof
 


class TestDisciplinaService(TestDisciplinaRepository,TestDisciplinaValidator):


    def setUp(self):
        TestDisciplinaRepository.setUp(self)
        TestDisciplinaValidator.setUp(self)


    def tearDown(self):
        TestDisciplinaRepository.tearDown(self)
        TestDisciplinaValidator.tearDown(self)


    def testDisciplinaService(self):
        TestDisciplinaRepository.testDisciplinaRepo(self)
        TestDisciplinaValidator.testDisciplinaValidator(self)
        self._servd = DisciplinaService(self._repo)
        self.assertEqual(self._servd.size(),0)
        self._servd.adaugaDisciplina(1, 'FP', 'Bogdan')
        self.assertEqual(self._servd.size(), 1)
        self._servd.modificaDisciplina(1, 'LC', 'Popescu')
        d1=Disciplina(1, 'LC', 'Popescu')
        self.assertEqual(self._servd.getAllDiscipline(), [d1])
        self._servd.adaugaDisciplina(2, 'Analiza','Milici')
        d2=Disciplina(2, 'Analiza','Milici')
        self.assertEqual(self._servd.getAllDiscipline(),[d1,d2])
        self.assertEqual(self._servd.gasesteDisciplina(2),d2)
        self._servd.adaugaDisciplina(3, 'FP', 'Milici')
        d3=Disciplina(3, 'FP', 'Milici')
        self.assertEqual(self._servd.cautaDisciplina('li'),[d2])
        p1=Prof('Popescu',1)
        p2=Prof('Milici',5)
        self.assertEqual(self._servd.cautaProfesor(),[p1,p2])
        self._servd.stergeDisciplina(1)
        self.assertEqual(self._servd.size(), 2)
        self.assertEqual(self._servd.getAllDiscipline(), [d2,d3])


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()