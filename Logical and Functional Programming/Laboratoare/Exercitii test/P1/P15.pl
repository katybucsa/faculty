%a)Sa se scrie un predicat care se va satisface daca o lista are numar
% par de elemente si va esua in caz contrar, fara sa se numere
% elementele listei.

%lP(L:list,N:number)
%L - lista data
%N - numar care indica daca un element e pe pozitie para sau impara
%model de flux (i,i)

lP([],N):-mod(N,2)=\=0,!.
lP([_|T],1):-lP(T,0),!.
lP([_|T],0):-lP(T,1).


%lgPara(L:lista)
%L - lista data
%model de flux (i)

lgPara(L):-lP(L,1).

% ************************************************************************
% b) Sa se elimine prima aparitie a elementului minim dintr-o lista de
% numere intregi.

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


%elimina1(L:list,R:list)
%L - lista data
% R - lista rezultata in urma stergerii primei aparitii a elementului
% minim
% model de flux (i,o)

elimina1([],[]):-!.
elimina1(L,R):- detMin(L,E),elim1(L,E,0,R).








