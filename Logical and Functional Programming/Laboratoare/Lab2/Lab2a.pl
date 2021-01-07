%a
%predecesor1(L,N,R)
%L - lista cu numarul reprezentat cifra cu cifra
%N - numarul ce reprezinta imprumutul la fiecare nivel
% R - rezultatul( lista cu predecesorul numarului reprezentat cifra cu
% cifra)
% model de flux: (i,o,o)

predecesor1([0],-1,[9]):-!.
predecesor1([H],0,[R]):- H>0, R is H-1,!.
%predecesor1([0|T],0,R):- predecesor1(T,0,R1),!,R=[0|R1].
predecesor1([0|T],I,R):- predecesor1(T,I1,R1),I1=0,!,R=[0|R1],I=0.
%predecesor1([0|T],-1,R):- predecesor1(T,-1,R1),!,R=[9|R1].
predecesor1([0|T],I,R):- predecesor1(T,I1,R1),I1= -1,!,R=[9|R1],I= -1.
% predecesor1([H|T],0,R):- H>0,predecesor1(T,I1,R1),H1 is H+I1,R=[H1|R1]
predecesor1([H|T],I,R):- H>0, predecesor1(T,I1,R1),H1 is H+I1, R=[H1|R1],I=0.



%predecesor(L,R)
%L - lista eterogena care contine numere si liste de cifre
% R - lista rezultat in care numerele reprezentate sub forma de lista au
% fost inlocuie cu predecesorul lor
% model de flux: (i,o)
predecesor(L,R):-predecesor1(L,_,[0|R1]),!,R=R1.
predecesor(L,R):-predecesor1(L,_,R).


%b
% predecesorlist(L,R)
% L – lista eterogena care contine numere si liste de      numere
% R – lista rezultat in care s-au inlocuit numerele reprezentate sub forma de lista cu predecesorul lor
% model de flux (i, o)

predecesorlist([],[]):-!.
predecesorlist([H|T],R):- is_list(H),
                          !,
                          predecesor(H,Rez),
                          predecesorlist(T,R1),
                          R=[Rez|R1].
predecesorlist([H|T],R):-predecesorlist(T,R1),R=[H|R1].





