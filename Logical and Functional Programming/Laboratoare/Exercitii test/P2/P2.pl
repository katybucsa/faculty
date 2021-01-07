%a)Sa se sorteze o lista cu pastrarea dublurilor. De ex:
% [4, 2, 6, 2, 3, 4] => [2, 2, 3, 4, 4, 6].

%detMin(L:list,M:int)
%L - lista data
%M - elementul minim din lista L
%model de flux (i,o)

detMin([E],E):-!.
detMin([H|T],H):-detMin(T,M1),H<M1,!.
detMin([_|T],M):-detMin(T,M).

%elim1(L:list,E:elem,N:int,R:list)
%L - lista data
%E - elementul minim din lista L
%N - numar(daca e 0 inseamna ca inca nu s-a sters prima aparitie a
% elementului E)
%R - lista rezultata in urma stergerii primei aparitii a
% elementului minim
%model de flux (i,i,i,o)

elim1([],_,_,[]):-!.
elim1([E|T],E,0,R):- elim1(T,E,1,R),!.
elim1([H|T],E,N,R):- elim1(T,E,N,R1),R=[H|R1].

% sorteaza(L:list,R:list)
% L - lista data
% R - lista sortata
% model de flux

sorteaza([],[]):-!.
sorteaza(L,R):- detMin(L,M),
                elim1(L,M,0,L1),
                sorteaza(L1,R1),
                R=[M|R1].








