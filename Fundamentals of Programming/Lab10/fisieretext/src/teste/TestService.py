'''
Created on Nov 29, 2017

@author: Andrei
'''
import unittest
from teste.TestRepo import TestRepo
from service.NoteService import NoteService
from domain.Student import Student
from domain.Materie import Materie
from domain.Nota import Nota

class TestService(TestRepo):


    def setUp(self):
        TestRepo.setUp(self)


    def tearDown(self):
        TestRepo.tearDown(self)


    def testService(self):
        TestRepo.testRepo(self)
        self._repo.add(Student(1, "ion"))
        self._repo.add(Student(2, "iona"))
        self._materierepo.add(Materie(1, "istorie"))
        self._materierepo.add(Materie(2, "mate"))
        self._noterepo.add
        (Nota(1, 1, 5))
        self._noterepo.add(Nota(2, 1, 7))
        self._noterepo.add(Nota(1, 2, 8))
        self._noterepo.add(Nota(2, 2, 9))
        self._noteServ = NoteService(self._repo,self._noterepo,self._materierepo) 
        materii = self._noteServ.celeMaiFaineMaterii()
        self.assertEqual(materii[0].get_nume_materie(), "mate")
        self.assertEqual(materii[1].get_nume_materie(), "istorie")
        for x in materii:
            print(x)
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()