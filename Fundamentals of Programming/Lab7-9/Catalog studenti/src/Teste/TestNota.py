
import unittest
from Teste.TestStudent import TestStudent
from Teste.TestDisciplina import TestDisciplina
from Domain.Nota import Nota


class TestNota(TestStudent,TestDisciplina):


    def setUp(self):
        TestStudent.setUp(self)
        TestDisciplina.setUp(self)
        self._n = 9.67
        self._altan = 8.45


    def tearDown(self):
        TestStudent.tearDown(self)
        TestDisciplina.tearDown(self)


    def testNota(self):
        TestStudent.testStudent(self)
        TestDisciplina.testDisciplina(self)
        self._nota=Nota(self._student, self._disciplina, self._n)
        self.assertEqual(self._nota.getStud(),self._student)
        self.assertEqual(self._nota.getDisc(),self._disciplina)
        self.assertEqual(self._nota.getNota(),self._n)
        self._nota.setStud(self._altstudent)
        self._nota.setDisc(self._altadisc)
        self.assertEqual(self._nota.getStud(),self._altstudent)
        self.assertEqual(self._nota.getDisc(),self._altadisc)
        self._altanota=Nota(self._altstudent,self._altadisc,self._altan)
        self.assertEqual(self._nota,self._altanota)
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()