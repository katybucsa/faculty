
import unittest
from Validare.Validator import ValidatorStudent, ValidatorException
from Teste.TestStudent import TestStudent
from Domain.Student import Student


class TestStudentValidator(TestStudent):


    def setUp(self):
        TestStudent.setUp(self)


    def tearDown(self):
        TestStudent.tearDown(self)


    def testStudentValidator(self):
        TestStudent.testStudent(self)
        self._valid = ValidatorStudent()
        self.assertEqual(len(self._valid),0)
        self._valid.valideazaStudent(self._student)
        with self.assertRaises(ValidatorException):
            self._valid.valideazaStudent(Student(1,'fdd3',19))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaStudent(Student(1,757,19))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaStudent(Student(-2,'Alex',19))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaStudent(Student(1,'',19))
        with self.assertRaises(ValidatorException):
            self._valid.valideazaStudent(Student(1,'Alex',10))
        self.assertEqual(len(self._valid),5)
        
        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()