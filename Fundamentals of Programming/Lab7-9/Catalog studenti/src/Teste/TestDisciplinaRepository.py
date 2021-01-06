
import unittest
from Teste.TestDisciplina import TestDisciplina
from Repository.Repository import FileDisciplinaRepository,RepositoryException
from Domain.Disciplina import Disciplina

class TestDisciplinaRepository(TestDisciplina):


    def setUp(self):
        TestDisciplina.setUp(self)
        self._numefis = 'discipline.txt'


    def tearDown(self):
        with open(self._numefis,'w') as f:
            f.write('')
        


    def testDisciplinaRepo(self):
        TestDisciplina.testDisciplina(self)
        self._repo=FileDisciplinaRepository(self._numefis)
        self.assertEqual(len(self._repo),0)
        self._repo.adaugaD(self._disciplina)
        self.assertEqual(len(self._repo),1)
        disc = self._repo.getD(self._altadiscident)
        self.assertEqual(disc.getNumeD(), 'FP')
        self.assertEqual(disc.getProfesor(),'Popescu')
        with self.assertRaises(RepositoryException):
            self._repo.adaugaD(self._disciplina)
        with self.assertRaises(RepositoryException):
            self._repo.stergeD(self._altadisc)
        self._repo.adaugaD(self._altadisc)
        self.assertEqual(len(self._repo),2)
        self._repo.modificaD(self._altadiscident)
        self.assertEqual(self._repo.getAllD(),[self._altadiscident,self._altadisc])
        self._repo.stergeD(self._altadisc)
        self.assertEqual(len(self._repo),1)
        with self.assertRaises(RepositoryException):
            self._repo.modificaD(self._altadisc)
        with self.assertRaises(RepositoryException):
            self._repo.getD(self._altadisc)
        self._repo.stergeD(self._altadiscident)
        self.assertEqual(len(self._repo),0)
        
        
        
        
        
        
        
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()