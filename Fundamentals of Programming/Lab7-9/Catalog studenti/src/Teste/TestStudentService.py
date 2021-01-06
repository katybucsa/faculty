
import unittest
from Teste.TestStudentRepository import TestStudentRepository
from Service.StudentService import StudentService
from Repository.Repository import RepositoryException
from Teste.TestStudentValidator import TestStudentValidator
from Validare.Validator import ValidatorException
from Domain.Student import Student

class TestStudentService(TestStudentRepository,TestStudentValidator):


    def setUp(self):
        TestStudentRepository.setUp(self)
        TestStudentValidator.setUp(self)


    def tearDown(self):
        TestStudentRepository.tearDown(self)
        TestStudentValidator.tearDown(self)

    def testStudentService(self):
        TestStudentRepository.testStudentRepo(self)
        TestStudentValidator.testStudentValidator(self)
        self._servs = StudentService(self._repo)
        self.assertEqual(self._servs.size(),0)
        self._servs.adaugaStudent(1,'Alex',20)
        self.assertEqual(self._servs.size(),1)
        with self.assertRaises(RepositoryException):
            self._servs.adaugaStudent(1,'Alin',19)
        self._servs.modificaStudent(1, 'Alin', 19)
        st1=Student(1, 'Alin', 19)
        with self.assertRaises(ValidatorException):
            self._servs.adaugaStudent(-1, 'Vlad', 20)
        with self.assertRaises(RepositoryException):
            self._servs.modificaStudent(3, 'Alina', 19)
        with self.assertRaises(ValidatorException):
            self._servs.modificaStudent(1, 'Vlad', 15)
        self.assertEqual(self._servs.getAllStudents(), [st1])
        self._servs.adaugaStudent(2,'Mihai',21)
        st2=Student(2,'Mihai',21)
        self.assertEqual(self._servs.getAllStudents(), [st1,st2])
        self.assertEqual(self._servs.gasesteStudent(1), st1)
        self._servs.adaugaStudent(3, 'Calin', 20)
        st3=Student(3, 'Calin', 20)
        self.assertEqual(self._servs.cautaStudent('lin'), [st1,st3])
        self.assertEqual(self._servs.getOrdonatVarsta(), [st2,st3,st1])
        self._servs.stergeStudent(1)
        self.assertEqual(self._servs.size(),2)
        self.assertEqual(self._servs.getAllStudents(),[st2,st3])
        self._servs.adaugaStudent(4, 'Mihai', 22)
        st4=Student(4, 'Mihai', 22)
        self.assertEqual(self._servs.getAllWithName('Mihai'),[st2,st4])
        
            
    
        
        
        
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()