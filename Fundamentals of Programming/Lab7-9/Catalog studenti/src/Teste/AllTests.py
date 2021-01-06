
from Teste.TestDisciplinaService import TestDisciplinaService
from Teste.TestStudentService import TestStudentService
from Teste.TestNotaService import TestNotaService

def runAllTests():
    TestStudentService().testStudentService()
    TestDisciplinaService().testDisciplinaService()
    TestNotaService().testNotaService()