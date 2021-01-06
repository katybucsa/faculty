#ifndef DICTIONAR_H
#define DICTIONAR_H

#define MAX 10 //numarul maxim de locatii din tabela

typedef int TCheie;
typedef int TValoare;

typedef struct TElem{
    TCheie c;
    TValoare v;
   }TElem;

//functia care da <hashCode>-ul unei chei

int hashCode(TCheie c);

//referire a clasei Iterator necesara folosirii instantelor de tip Iterator
class Iterator;

//referire a clasei Nod
class Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod *PNod;

class Nod
	{
	private:
		TCheie c;
		TValoare v;
		PNod urm;
	public:
	    friend class Dictionar;
	    friend class Iterator;
        //constructor
		Nod(TCheie e, TValoare v, PNod urm);
};

//clasa care defineste reprezentarea si functiile TAD Dictionar

//reprezentare folosind o TD - rezolvare coliziuni prin liste independente
class Dictionar {
private:
    int m; //numarul de locatii din tabela de dispersie
	PNod l[MAX]; //listele independente - vector static

	//functia de dispersie
	int d(TCheie c);

public:
//clasa Iterator trebuie sa fie prietena cu clasa Dictionar
//pentru a putea avea acces la elementele ei private
	friend class Iterator;
//constructorul implicit al dictinarului
	Dictionar();
//adauga o pereche (cheie, valoare) in dictionar
	void adauga(TCheie c, TValoare v);
//se returneaza iterator pe dictionar
	Iterator iterator() const;

//alte operatii pe dictionar...constructor copiere, cautare, stergere, dimensiune, etc

// destructorul dictionarului
	~Dictionar();

};

//iterator pe dictionar
class Iterator {
private:
//pentru a construi un iterator pe un dictionar este necesar ca un pointer sau referinta la acesta sa ii fie oferit constructorului
	Iterator(const Dictionar& d);
    void deplasare();

//contine o referinta catre dictionarul care se itereaza
	const Dictionar& dct;
//locatia curenta din tabela
    int poz;
//retine pozitia curenta in interiorul listei de la locatia curenta - adresa Nodului curent din lista asociata
	PNod curent;
public:

    friend class Dictionar;

//reseteaza pozitia iteratorului la inceputul dictionarului
	void prim();
//muta iteratorul pe urmatoarea pozitie in cadrul dictionarului
	void urmator();
//verifica daca iteratorul e valid (indica un element al dictionarului)
	bool valid() const;
//returneaza valoarea curenta a elementului din dictionar
	TElem element() const;
};

#endif
