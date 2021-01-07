%a)Sa se scrie un predicat care testeaza daca o lista este multime.

%apare(E:elem,L:list)
%E - elementul cautat
%L - lista in care se cauta
%model de flux (i,i)

apare(E,[E|_]):-!.
apare(E,[_|T]):-apare(E,T).


%eMultime(L:list)
%L - lista data
%model de flux (i)

eMultime([]):-!.
eMultime([H|T]):- \+apare(H,T),eMultime(T).

% ************************************************************************
% b)Sa se scrie un predicat care elimina primele 3 aparitii ale unui
% element dintr-o lista. Daca elementul apare mai putin de 3 ori, se va
% elimina de cate ori apare.

%elim3(L:list, E:elem,N:elem,R:list)
% L - lista data
% E - elementul ala carui prime 3 aparitii se vor elimina din lista L
% N - numarul de aparitii ale elementului E la un moment dat
% R - lista rezultata in urma eliminarii elementului E
% model de flux (i,i,i,o)

elim3([],_,_,[]):-!.
elim3([E|T],E,N,R):- N<3,!,N1 is N+1,elim3(T,E,N1,R).
elim3([H|T],E,N,R):-elim3(T,E,N,R1),R=[H|R1].


% elimina3(L:list,E:elem,R:list)
% L - lista data
% E - elementul ala carui prime 3 aparitii se vor elimina din lista L
% R - lista rezultata in urma eliminarii elementului E
% model de flux (i,i,o)


elimina3(L,E,R):-elim3(L,E,0,R).







