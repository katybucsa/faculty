
import unittest
from Teste.TestStudent import TestStudent
from Repository.Repository import FileStudentRepository, RepositoryException
from Domain.Student import Student

class TestStudentRepository(TestStudent):


    def setUp(self):
        TestStudent.setUp(self)
        self._numefis = 'studenti.txt'
        


    def tearDown(self):
        with open(self._numefis,'w')as f:
            f.write("")   


    def testStudentRepo(self):
        TestStudent.testStudent(self)
        self._repo = FileStudentRepository(self._numefis)
        self.assertEqual(len(self._repo),0)
        self._repo.adaugaSt(self._student)
        self.assertEqual(len(self._repo),1)
        st= self._repo.getSt(self._altstudentidentic)
        self.assertEqual(st.getNumeSt(),'Andrei')
        with self.assertRaises(RepositoryException):
            self._repo.adaugaSt(self._student)
        with self.assertRaises(RepositoryException):
            self._repo.getSt(self._altstudent) 
        self._repo.modificaSt(self._altstudentidentic) 
        with self.assertRaises(RepositoryException):
            self._repo.modificaSt(self._altstudent) 
        with self.assertRaises(RepositoryException):
            self._repo.stergeSt(self._altstudent)
        self._repo.adaugaSt(self._altstudent)
        self.assertEqual(len(self._repo),2)
        self.assertEqual(self._repo.getAllSt(),[self._student,self._altstudent])
        self._repo.stergeSt(self._altstudent)
        self.assertEqual(len(self._repo),1)
        self._repo.stergeSt(self._student)
        self.assertEqual(len(self._repo),0)
        
         
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()