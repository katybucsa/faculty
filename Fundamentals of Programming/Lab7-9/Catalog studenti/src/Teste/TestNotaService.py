
import unittest
from Teste.TestNotaRepository import TestNotaRepository
from Teste.TestNotaValidator import TestNotaValidator
from Service.NotaService import NotaService


class TestNotaService(TestNotaRepository,TestNotaValidator):


    def setUp(self):
        TestNotaRepository.setUp(self)
        TestNotaValidator.setUp(self)


    def tearDown(self):
        TestNotaRepository.tearDown(self)
        TestNotaValidator.tearDown(self)


    def testNotaService(self):
        TestNotaRepository.testNotaRepo(self)
        TestNotaValidator.testNotaValidator(self)
        self._servn = NotaService(self._repo)
        self.assertEqual(self._servn.size(),0)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()