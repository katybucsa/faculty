
import unittest
from Teste.TestNota import TestNota
from Repository.Repository import FileNoteRepository, RepositoryException
from Domain.Nota import Nota

class TestNotaRepository(TestNota):


    def setUp(self):
        TestNota.setUp(self)
        self._numefis='testnote.txt'
        self.__numefis1='teststudenti.txt'
        self.__numefis2='testdiscipline.txt'


    def tearDown(self):
        with open(self._numefis,'w') as f:
            f.write('')


    def testNotaRepo(self):
        TestNota.testNota(self)
        self._repo=FileNoteRepository(self._numefis)
        self.assertEqual(len(self._repo),0)
        self._repo.adaugaN(self._nota)
        with self.assertRaises(RepositoryException):
            self._repo.adaugaN(self._nota)
        self._repo.modificaNota(Nota(self._altstudent,self._altadisc,9.8))
        self.assertEqual(self._repo.getAllGr(),[self._nota])
        self._repo.stergeNota(self._nota)
        self.assertEqual(len(self._repo), 0)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()