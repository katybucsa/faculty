%a) Definiti un predicat care determina predecesorul unui numar
% reprezentat cifra cu cifra intr-o lista. De ex: [1, 9, 3, 6, 0, 0] =>
% [1, 9, 3, 5, 9, 9].

%predecesor1(L,N,R)
%L - lista cu numarul reprezentat cifra cu cifra
%N - numarul ce reprezinta imprumutul la fiecare nivel
% R - rezultatul( lista cu predecesorul numarului reprezentat cifra cu
% cifra)
% model de flux: (i,o,o)

predecesor1([0],-1,[9]):-!.
predecesor1([H],0,[R]):- H>0, R is H-1,!.
predecesor1([0|T],I,R):- predecesor1(T,I1,R1),I1= -1,!,R=[9|R1],I= -1.
predecesor1([H|T],I,R):- H>0, predecesor1(T,I1,R1),H1 is H+I1, R=[H1|R1],I=0.



%predecesor(L,R)
%L - lista eterogena care contine numere si liste de cifre
% R - lista rezultat in care numerele reprezentate sub forma de lista au
% fost inlocuie cu predecesorul lor
% model de flux: (i,o)
predecesor(L,R):-predecesor1(L,_,[0|R1]),!,R=R1.
predecesor(L,R):-predecesor1(L,_,R).



%predecesor1([0|T],0,R):- predecesor1(T,0,R1),!,R=[0|R1].
%predecesor1([0|T],I,R):- predecesor1(T,I1,R1),I1=0,!,R=[0|R1],I=0.
%predecesor1([0|T],-1,R):- predecesor1(T,-1,R1),!,R=[9|R1].
% predecesor1([H|T],0,R):- H>0,predecesor1(T,I1,R1),H1 is H+I1,R=[H1|R1]




















