'''
Created on Nov 29, 2017

@author: Andrei
'''
import unittest
from domain.Student import Student

class TestStudent(unittest.TestCase):


    def setUp(self):
        self._ident = 1
        self._nume = "ion"
        self._altident = 2
        self._altnume = "vasi"


    def tearDown(self):
        pass


    def testStudent(self):
        self._student = Student(self._ident,self._nume)
        self._altstudentidentic = Student(self._ident,"geo")
        self._altstudent = Student(self._altident,self._altnume)
        self.assertEqual(self._student, Student(self._ident,"coco"))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()