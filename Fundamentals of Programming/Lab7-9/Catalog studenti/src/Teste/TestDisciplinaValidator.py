
import unittest
from Teste.TestDisciplina import TestDisciplina
from Validare.Validator import ValidatorException, ValidatorDisciplina
from Domain.Disciplina import Disciplina

class TestDisciplinaValidator(TestDisciplina):


    def setUp(self):
        TestDisciplina.setUp(self)


    def tearDown(self):
        TestDisciplina.tearDown(self)


    def testDisciplinaValidator(self):
        TestDisciplina.testDisciplina(self)
        self._valid=ValidatorDisciplina()
        self.assertEqual(len(self._valid),0)
        self._valid.valideazaDisciplina(self._disciplina)
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,'mate1','Pop'))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,123,'Pop'))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,'mate','Pop113'))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,'mate',242))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(-11,'mate','Pop'))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,'','Pop'))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaDisciplina(Disciplina(1,'mate',''))
        self.assertEqual(len(self._valid),7)
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()