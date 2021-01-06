class Student:
    
    
    def __init__(self, _ident, _nume):
        self.__ident = _ident
        self.__nume = _nume

    def get_ident(self):
        return self.__ident


    def get_nume(self):
        return self.__nume


    def set_nume(self, value):
        self.__nume = value

    def __eq__(self,other):
        return isinstance(other,self.__class__) and self.__ident == other.__ident

    @staticmethod
    def citesteDinStr(line):
        cuvant = line.split(";")
        return Student(int(cuvant[0]),cuvant[1])
    
    @staticmethod
    def scrieInStr(stud):
        return str(stud.get_ident())+";"+stud.get_nume()