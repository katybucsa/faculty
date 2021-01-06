from Repository.Repository import StudentRepository, DisciplinaRepository,NotaRepository,FileStudentRepository,FileDisciplinaRepository,FileNoteRepository
from Service.StudentService import StudentService
from Service.DisciplinaService import DisciplinaService
from UI.Console import Console
from Service.NotaService import NotaService
from Teste.AllTests import runAllTests


repost=FileStudentRepository('studenti.txt')
servs=StudentService(repost)
repodis=FileDisciplinaRepository('discipline.txt')
servd=DisciplinaService(repodis)
reponot=FileNoteRepository('note.txt')
servn=NotaService(reponot)
console=Console(servs,servd,servn)
console.run()

