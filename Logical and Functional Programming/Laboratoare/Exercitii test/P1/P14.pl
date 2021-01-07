%a)Sa se scrie un predicate care testeaza egalitatea a doua multimi,
% fara sa faca apel la diferenta a doua multimi.

%apartine(L:list,X:list)
%L - lista a carei elemente sunt cautate in lista X
%X - lista in care se verifica daca elementele listei L ii apartin
%model de flux (i,i)

apartine([],_):-!.
apartine([H|T],X):- apare(H,X),apartine(T,X).



%egalitate(A:list,B:list)
%A - prima multime data
%B - a 2 a multime data
%model de flux (i,i)

egalitate(A,B):-apartine(A,B),apartine(B,A).

% ************************************************************************
%b)Definiti un predicat care selecteaza al n-lea element al unei liste.

%selecteaza(L:list,N:int,E:elem)
%L - lista data
%N - pozitia elementului care trebuie selectat
%E - elementul de pe pozitia N
%model de flux (i,i,o)

selecteaza([],_,_):-write("Nu exista elementul de pe pozitia introdusa!"),nl,!.
selecteaza([H|_],1,H):-!.
selecteaza([_|T],N,E):- N1 is N-1, selecteaza(T,N1,E).
















