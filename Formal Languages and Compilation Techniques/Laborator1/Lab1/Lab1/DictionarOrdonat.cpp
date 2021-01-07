#include <iostream>
#include <vector>
#include <string>
#include <stack>
#include <queue>


typedef std::string TCheie;
typedef int TValoare;
class TElement;
typedef void(*Relatie)(TElement);
class IteratorDo;
class Nod;
class DictionarOrdonat;



class DictionarOrdonatExceptie {
private:
	std::string mesaj;
public:
	DictionarOrdonatExceptie(const std::string& msg) :mesaj{ msg } {}
	const std::string& getMsg() const noexcept {
		return mesaj;
	}
};


class TElement {
private:
	TCheie c;
	TValoare v;
public:
	TElement(const TCheie& c, const TValoare& v) :c{ c }, v{ v } {}
	TElement() = default;
	friend std::ostream& operator<<(std::ostream& s, TElement e) {
		s << "Nume: " << e.c << "\t" << "Nota: " << e.v << std::endl;
		return s;
	}
	const TCheie& getCheie() const noexcept {
		return c;
	}
	const TValoare& getValoare() const noexcept {
		return v;
	}
	friend class Nod;
	friend class DictionarOrdonat;
	friend bool cmp(const TElement&, const TElement&);
};


inline bool cmp(const TElement& e1, const TElement& e2) {
	return e1.c < e2.c;
}


class Nod {
private:
	TElement e;
	Nod* st;
	Nod* dr;
public:
	friend class TElement;
	friend class DictionarOrdonat;
	Nod(const TElement&, Nod*, Nod*);
	TElement element() noexcept;
	Nod* stanga() noexcept;
	Nod* dreapta() noexcept;
};


class DictionarOrdonat {
private:
	Nod* rad;
	bool(*R)(const TElement&, const TElement&);
	Nod* adauga_recursiv(Nod*, Nod*);
	Nod* cauta_recursiv(Nod*, const TCheie&);
	Nod* sterge_recursiv(Nod*, const TCheie&);
	Nod* minim(Nod*);
	size_t dim_recursiv(Nod*, size_t);
public:
	friend class Nod;
	friend class IteratorDo;
	DictionarOrdonat(bool(*cmp)(const TElement&, const TElement&)) noexcept :rad{ NULL }, R{ cmp } {}
	void adauga(const TCheie&, const TValoare&);
	TValoare cauta(const TCheie&);
	void sterge(const TCheie&);
	size_t dim();
	bool vid();
	IteratorDo iterator() const noexcept;
	~DictionarOrdonat() {

	}
};


class IteratorDo {
private:
	Nod* curent;
	const DictionarOrdonat& dord;
	std::stack<Nod*> s;
	
	IteratorDo(const DictionarOrdonat& dord)noexcept :dord{ dord }, curent{ dord.rad } {
		//inordine();//constructorul stivei
	}
public:
	std::queue<std::string> inordine();
	
	friend class DictionarOrdonat;

	TElement element() noexcept;

	bool valid() noexcept;

	void urmator() noexcept;

};

////Nod 

inline Nod::Nod(const TElement& e, Nod* n1, Nod* n2) :e{ e }, st{ n1 }, dr{ n2 } {}

inline TElement Nod::element() noexcept {
	return e;
}

inline Nod* Nod::stanga() noexcept {
	return st;
}

inline Nod* Nod::dreapta() noexcept {
	return dr;
}

////DICTIONAR ORDONAT


inline Nod* DictionarOrdonat::adauga_recursiv(Nod* rad, Nod* nod) {

	if (rad == NULL)
		rad = nod;
	else {
		if (R(nod->e, rad->e))		//se cauta in subarborele stang
			rad->st = adauga_recursiv(rad->st, nod);

		else						//se adauga in subarborele drept
			rad->dr = adauga_recursiv(rad->dr, nod);
	}
	return rad;
}

inline Nod* DictionarOrdonat::cauta_recursiv(Nod* rad, const TCheie& c) {
	if (rad == NULL || rad->e.c == c)
		return rad;
	else {
		if (c < rad->e.c)			//se cauta in subarborele stang
			cauta_recursiv(rad->st, c);
		else						//se cauta in subarborele drept
			cauta_recursiv(rad->dr, c);
	}
}

inline Nod* DictionarOrdonat::sterge_recursiv(Nod* rad, const TCheie& c) {
	Nod* temp;
	Nod* repl;
	if (rad == NULL)
		return NULL;
	else {
		if (c < rad->e.c) {					//se sterge din subarborele stang
			rad->st = sterge_recursiv(rad->st, c);
			return rad;
		}
		else {
			if (c > rad->e.c) {				//se sterge din subarborele drept
				rad->dr = sterge_recursiv(rad->dr, c);
				return rad;
			}
			else { // am ajuns la nodul care trebuie sters
				if (rad->st != NULL && rad->dr != NULL) {		//nodul are si subarbore stang si subarbore drept
					temp = minim(rad->dr);
					//se muta cheia minima in rad
					rad->e = temp->e;
					//se sterge nodul cu cheia minima din subarborele drept
					rad->dr = sterge_recursiv(rad->dr, rad->e.c);
					return rad;
				}
				else {
					temp = rad;
					if (rad->st == NULL)		//nu exista subarbore stang
						repl = rad->dr;
					else						//nu exista subarbore drept
						repl = rad->st;
				}
				delete temp; //eliberam memoria
				return repl;
			}
		}

	}
}

inline Nod* DictionarOrdonat::minim(Nod * p) {
	while (p->st != NULL)
		p = p->st;
	return p;
}

inline size_t DictionarOrdonat::dim_recursiv(Nod * rad, size_t dm) {
	if (rad == NULL)
		return 0;
	return 1 + dim_recursiv(rad->st, dm + 1) + dim_recursiv(rad->dr, dm + 1);
}


inline void DictionarOrdonat::adauga(const TCheie & cheie, const TValoare & valoare) {
	//pre: elem este te tip TElement
	//post: elem a fost adaugat in dictionarul ordonat sau se arunca exceptie daca elementul exista deja
	TElement elem{ cheie,valoare };
	Nod* nod = new Nod(elem, NULL, NULL);
	//try {
	if (cauta(elem.c) == NULL) {
		rad = adauga_recursiv(rad, nod); return;
	}
	//}
	//catch (DictionarOrdonatExceptie) { rad = adauga_recursiv(rad, nod); return; }
	//throw DictionarOrdonatExceptie("Element existent!\n");
}

inline TValoare DictionarOrdonat::cauta(const TCheie & c) {
	//pre: c este de tip TCheie
	//post: returneaza valoarea asociata cheii c sau se arunca exceptie daca aceasta nu exista
	Nod* rez = cauta_recursiv(rad, c);
	if (rez == NULL)
		return NULL;
	//throw DictionarOrdonatExceptie("Element inexistent!\n");
	return rez->e.v;
}

inline void DictionarOrdonat::sterge(const TCheie & c) {
	//pre: c are tipul TCheie 
	//post: daca exista elementul cu cheia c in dictionarul ordonat atunci acesta a fost sters 
	//se arunca exceptie daca cheia c nu exista
	cauta(c);
	rad = sterge_recursiv(rad, c);
}

inline size_t DictionarOrdonat::dim() { //returneaza numarul de elemente ale dictionarului
	return dim_recursiv(rad, 0);
}

inline bool DictionarOrdonat::vid() { //returneaza adevarat daca dictionarul este vid si fals, altfel
	return rad == NULL;
}


inline IteratorDo DictionarOrdonat::iterator() const noexcept {//creeaza iterator pentru dictionarul ordonat curent
	return IteratorDo(*this);
}

////ITERATOR

inline std::queue<std::string> IteratorDo::inordine(){
	Nod* r = dord.rad;
	std::queue<std::string> q;
	while (!s.empty() || r != NULL) {
		while (r != NULL) {//se adauga in stiva ramura stanga a lui r
			s.push(r);
			r = r->stanga();
		}
		r = s.top();
		s.pop();//se sterge nodul din varful stivei
		//tipareste
		q.push(r->element().getCheie() + "," + std::to_string(r->element().getValoare()));
		r = r->dreapta();
	}
	return q;
}


inline bool IteratorDo::valid() noexcept {//validitatea iteratorului
	return curent != NULL || !s.empty();
}

inline TElement IteratorDo::element() noexcept {//elementul curent al iteratorului
	while (curent != NULL) {//se adauga in  stiva ramura stanga a elementului curent
		s.push(curent);
		curent = curent->stanga();
	}
	TElement e = s.top()->element();
	curent = s.top();
	s.pop();//se sterge elementul din varful stivei
	return e;
}

inline void IteratorDo::urmator() noexcept {//deplaseaza iteratorul
	curent = curent->dreapta();
}