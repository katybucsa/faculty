%a)Sa se scrie un predicat care intoarce reuniunea a 2 multimi.

%apare(E:elem,L:list)
%E - elementul cautat
%L - lista in care se cauta
%model de flux (i,i)

apare(E,[E|_]):-!.
apare(E,[_|T]):-apare(E,T).


%adauga(A:list,B:list,R:list)
% A - lista a carei elemente vor fi adaugate in colectoare daca nu apar
% deja in ea
%B - colectoare care initial contine toate elementele multimii B
%R - lista rezultat
%model de flux (i,i,o)

adauga([],R,R):-!.
adauga([H|T],C,R):- \+apare(H,C),!,C1=[H|C],adauga(T,C1,R).
adauga([_|T],C,R):- adauga(T,C,R).

%reuniune(A:list,B:list,R:list)
%A - prima multime reprezentata sub forma de list
%B - a -a multime reprezentata sub forma de list
% R - multimea(reprezentata sub forma de lista) rezultata prin reuniunea
% multimilor A si B
% model de flux (i,i,o)


reuniune(A,B,R):-adauga(A,B,R).

% ************************************************************************
% b)Sa se scrie un predicat care, primind o lista, intoarce multimea
% tuturor perechilor din lista. De ex, cu [a,b,c,d] va produce
% [[a,b],[a,c],[a,d],[b,c],[b,d],[c,d]].

%adaugaSfarsit(E:elem,L:lsit,R:lsit)
%E - elementul care se adauga
%L - lista in care se adauga
%R - lista rezultata dupa adaugarea elementului E
%model de flux (i,i,o)

adaugaSfarsit(E,[],[E]):-!.
adaugaSfarsit(E,[H|T],R):-adaugaSfarsit(E,T,R1),R=[H|R1].


%adaugaP(E:elem,L:lsit,C:list,R:list)
%E - primul element din pereche
%L - lista elementelor cu care E face pereche
%C - colectoare
%R - lista rezultata dupa inserarea perechilor
%model de flux (i,i,i,o)

adaugaP(_,[],R,R):-!.
adaugaP(E,[H|T],C,R):- C1=[[E,H]|C],adaugaP(E,T,C1,R).

%perechi(L;list,R:list)
%L - lista data
%R - lista perechilor elementelor listei L
%model de flux (i,o)

perechi([],[]):-!.
perechi([H|T],R):- perechi(T,R1),adaugaP(H,T,R1,R).







