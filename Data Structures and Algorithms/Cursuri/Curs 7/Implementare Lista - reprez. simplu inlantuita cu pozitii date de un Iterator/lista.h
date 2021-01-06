#ifndef LISTA_H
#define LISTA_H

//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a clasei Iterator necesara folosirii instantelor de tip Iterator
class Iterator;

//referire a clasei Nod
class Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod *PNod;

class Nod
	{
	public:
	    friend class Lista;
        //constructor
		Nod(TElem e, PNod urm);
		TElem element();
		PNod urmator();
	private:
		TElem e;
		PNod urm;
};

//clasa care defineste reprezentarea si functiile TAD Lista

//pozitiile expuse de lista sunt date de un iterator pe lista
class Lista {
private:
//prim este adresa primului Nod din lista
	PNod _prim;
public:
//clasa Iterator trebuie sa fie prietena cu clasa Lista
//pentru a putea avea acces la elementele ei private
	friend class Iterator;
//constructorul implicit al unei liste
	Lista();
//adauga un element de tipul TElem la sfarsitul listei
	void adaugaSfarsit(TElem elem);

//adauga un element de tipul TElem dupa o pozitie data de un Iterator - post: daca Iteratorul nu este valid, se adauga la sfarsit
	void adaugaDupa(Iterator poz, TElem elem);

//prima pozitie din lista
	Iterator prim() const;

//alte operatii pe lista...constructor copiere, aduagari, cautari, stergeri, dimensiune, etc


// destructorul listei
	~Lista();

};

//iterator unidirectional pe lista
class Iterator {
private:
//pentru a construi un iterator pe o lista este necesar ca un pointer sau referinta la aceasta sa ii fie oferit constructorului
	Iterator(const Lista& lst);

//contine o referinta catre lista care se itereaza
	const Lista& lista;
//retine pozitia curenta in interiorul listei - adresa Nodului curent din lista asociata
	PNod curent;
public:

    friend class Lista;

//reseteaza pozitia iteratorului la inceputul listei
	void prim();
//muta iteratorul pe urmatoarea pozitie in cadrul listei
	void urmator();
//verifica daca iteratorul e valid (indica un element al listei)
	bool valid() const;
//returneaza valoarea curenta a elementului din lista
	TElem element() const;
};

#endif
