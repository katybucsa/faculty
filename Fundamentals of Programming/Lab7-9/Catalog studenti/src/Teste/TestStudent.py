
import unittest
from Domain.Student import Student


class TestStudent(unittest.TestCase):


    def setUp(self):
        self._idstud = 1
        self._numest = 'Andrei'
        self._varsta = 19
        self._altidstud = 2
        self._altnumest = 'Mihai'
        self._altavarsta = 18
        


    def tearDown(self):
        pass


    def testStudent(self):
        self._student = Student(self._idstud,self._numest,self._varsta)
        self.assertEqual(self._student.getIdstud(), 1)
        self.assertEqual(self._student.getNumeSt(), 'Andrei')
        self.assertEqual(self._student.getVarsta(), 19)
        self._altstudentidentic = Student(self._idstud,'Alex',20)
        self._altstudent = Student(self._altidstud,self._altnumest,self._altavarsta)
        self.assertLess(self._altstudent, self._student)
        self.assertEqual(self._student, self._altstudentidentic)
        self._altstudent.setNume('Robert')
        self._altstudent.setVarsta(21)
        self.assertEqual(self._altstudent.getNumeSt(), 'Robert')
        self.assertEqual(self._altstudent.getVarsta(), 21)
        
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()