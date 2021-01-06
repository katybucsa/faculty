
import unittest
from Teste.TestNota import TestNota
from Validare.Validator import ValidatorNota, ValidatorException
from Domain.Nota import Nota

class TestNotaValidator(TestNota):


    def setUp(self):
        TestNota.setUp(self)


    def tearDown(self):
        TestNota.tearDown(self)


    def testNotaValidator(self):
        TestNota.testNota(self)
        self._valid=ValidatorNota()
        self._valid.valideazaNota(self._nota)
        self._valid.valideazaNota(self._altanota)
        with self.assertRaises(ValidatorException):
            self._valid.valideazaNota(Nota(self._student,self._disciplina, -7))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaNota(Nota(self._student,self._disciplina, 17))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()