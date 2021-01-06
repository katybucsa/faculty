'''
Created on 23 Jan 2018

@author: ASUS
'''
from repository.StudentRepository import StudentRepository 
from repository.LabRepository import LabRepository 
from ui.LabService import LabService 
from ui.LabUI import Console


if __name__ == '__main__':
    repost=StudentRepository('student.txt')
    repol=LabRepository('labs.txt')
    service=LabService(repost,repol)
    console=Console(service)
    console.run()
    