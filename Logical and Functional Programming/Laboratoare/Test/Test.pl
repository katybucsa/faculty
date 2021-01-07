%PROLOG 17.Sa se sorteze o lista cu eliminarea dublurilor.

%detMin(L:list,M:int)
%L - lista in care se cauta elementul minim
%M - elementul minim din lista L
%model de flux (i,o)

detMin([M],M):-!.
detMin([H|T],H):- detMin(T,M1),H<M1,!.
detMin([_|T],M):- detMin(T,M).

%eliminaEl(L:list,E:int,R:list)
%L - lista din care se vor elimina toate aparitiile elementului E
%E - elementul ale carui aparitii vor fi eliminate
%R - lista dupa eliminarea tuturor aparitiilor elementului E
%model de flux (i,i,o)

eliminaEl([],_,[]):-!.
eliminaEl([E|T],E,R):-eliminaEl(T,E,R),!.
eliminaEl([H|T],E,R):-eliminaEl(T,E,R1),R=[H|R1].


%sorteaza(L:list,R:list)
%L - lista data
%R - lista initiala sortata cu eliminarea dublurilor
%model de flux (i,o)

sorteaza([],[]):-!.
sorteaza(L,R):- detMin(L,M),
                eliminaEl(L,M,R1),
                sorteaza(R1,R2),
                R=[M|R2].
