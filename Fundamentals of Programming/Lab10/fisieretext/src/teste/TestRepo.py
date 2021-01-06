'''
Created on Nov 29, 2017

@author: Andrei
'''
import unittest
from teste.TestStudent import TestStudent
from repository.Repository import Repository,RepoException,FileRepository
from domain.Student import Student

class TestRepo(TestStudent):


    def setUp(self):
        TestStudent.setUp(self)
        self._filename = "studenti.txt"


    def tearDown(self):
        with open(self._filename,"w") as f:
            f.write("")
        

    def testRepo(self):
        TestStudent.testStudent(self)
        self._repo = FileRepository(self._filename,Student.citesteDinStr,Student.scrieInStr)
        self._materierepo = Repository()
        self._noterepo = Repository()
        self.assertEqual(0, self._repo.size())
        self._repo.add(self._student)
        self.assertEqual(1, self._repo.size())
        st = self._repo.find(self._altstudentidentic)
        self.assertEqual("ion", st.get_nume())
        with self.assertRaises(RepoException):
            self._repo.add(self._student)
        self._repo.upd(self._altstudentidentic)
        with self.assertRaises(RepoException):
            self._repo.find(self._altstudent)
        with self.assertRaises(RepoException):
            self._repo.upd(self._altstudent)
        self.assertEqual([self._altstudentidentic], self._repo.getAll())
        self._repo.rem(self._student)
        with self.assertRaises(RepoException):
            self._repo.rem(self._altstudentidentic)
        
        
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()