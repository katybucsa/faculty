#ifndef COLECTIE_H
#define COLECTIE_H
//capacitatea vectorului intern
#define CAPACITATE 100

//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a clasei Iterator necesara folosirii instantelor de tip Iterator
//in definitia clasei Colectie inainte de definitia propriu-zisa a clasei Iterator
class Iterator;

//clasa care defineste structura si functiile TAD Colectie
class Colectie {
private:
//vectorul static de elemente de tip TElem (cu spatiu fix de memorare - CAPACITATE)
	TElem elems[CAPACITATE];
//numarul de elemente le colectie
	int len;
public:
//clasa Iterator trebuie sa fie prietena cu clasa Colectie
//pentru a putea avea acces la elementele ei private
	friend class Iterator;
//constructorul implicit al unei colectii
	Colectie();
//adauga un element de tipul TElem in colectie
	void adauga(TElem elem);

//sterge un element de tipul TElem din colectie
	void sterge(TElem elem);

//verifica daca un element de tiupul TElem se afla in colectie
	bool cauta(TElem elem);

//intoarce numarul de elemente din colectie;
	int dim() const;

//alte operatii pe colectie...

//expune un pointer la un iterator pentru parcurgere unidirectionala a colectiei

	Iterator iterator() const;

// destructorul colectiei
	~Colectie() {
	}

};
//iterator unidirectional pe colectie
class Iterator {
private:
//contine un pointer la colectia pe care se itereaza
	const Colectie *colectie;
//retine pozitia curenta in interiorul vectorului intern folosit in reprezentarea colectiei
	int curent;
public:

//pentru a construi un iterator pe o colectie este necesar ca un pointer la aceasta sa ii fie oferit constructorului
	Iterator(const Colectie* col);
//reseteaza pozitia iteratorului la inceputul vectorului intern al colectiei
	void prim();
//muta iteratorul pe urmatoarea pozitie in cadrul colectiei
	void urmator();
//verifica daca iteratorul e valid (indica un element al colectiei)
	bool valid() const;
//returneaza valoarea curenta a elementului din colectie
	TElem element() const;
};

#endif
