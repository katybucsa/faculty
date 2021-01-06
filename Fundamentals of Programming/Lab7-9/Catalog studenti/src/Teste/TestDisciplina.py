
import unittest
from Domain.Disciplina import Disciplina


class TestDisciplina(unittest.TestCase):


    def setUp(self):
        self._idDisc = 1
        self._numed = 'FP'
        self._prof = 'Popescu'
        self._altidDisc = 2
        self._altnumed = 'LC'
        self._altprof= 'Andrei'


    def tearDown(self):
        pass


    def testDisciplina(self):
        self._disciplina = Disciplina(self._idDisc,self._numed,self._prof)
        self.assertEqual(self._disciplina.getIdDisciplina(),1)
        self.assertEqual(self._disciplina.getNumeD(), 'FP')
        self.assertEqual(self._disciplina.getProfesor(),'Popescu')
        self._altadiscident=Disciplina(self._idDisc,'Algebra','Pop')
        self.assertEqual(self._disciplina,self._altadiscident)
        self._altadisc = Disciplina(self._altidDisc,self._altnumed,self._altprof)
        self._altadisc.setNume('Analiza')
        self._altadisc.setProfesor('Milici')
        self.assertEqual(self._altadisc.getNumeD(),'Analiza')
        self.assertEqual(self._altadisc.getProfesor(),'Milici')


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()