% a) Definiti un predicat care determina produsul unui numar reprezentat
% cifra cu cifra intr-o lista cu o anumita cifra. De ex: [1 9 3 5 9 9] *
% 2=> [3 8 7 1 9 8].

%produs1(L:list,C:digit,P:int,V:int,R:list)
%L - lista data
%C - cifra data
%P - catul impartirii la 10 a lui C*un element din L
% V - daca are valoarea 0 inseamna ca ma aflu pe pozitia primului element
% in lista
%R - lista; rezultatul inmultirii numarului reprezentat in
% lista L cu cifra C model de flux (i,i,o,o)


produs1([],_,0,_,[]):-!.
produs1([H|T],C,P,0,R):- produs1(T,C,P1,1,R1),
                         I is H*C+P1,
                         Rest is mod(I,10),
                         P is div(I,10),
                         R=[P,Rest|R1],!.
produs1([H|T],C,P,1,R):- produs1(T,C,P1,1,R1),
                         I is H*C+P1,
                         Rest is mod(I,10),
                         P is div(I,10),
                         R=[Rest|R1].



%produs(L:list,C:digit,R:list)
%L - lista data
%C - cifra data
%R - rezultatul inmultirii numarului reprezentat in lista L cu cifra C
%model de flux (i,i,o)

produs([_|_],0,[0]):-!.
produs(L,C,R):-produs1(L,C,_,0,R1),R1=[0|X],R=X,!.
produs(L,C,R):-produs1(L,C,_,0,R).

