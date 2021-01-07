%a)Sa se intercaleze un element pe pozitia a n-a a unei liste.
%intercalare(L:list,E:elem,N;int,R:list)
%L - lista data
%E - elementul care va fi inserat
%N - pozitia pe care va fi inserat elementul E
%R - rezultatul dupa inserare
%model de flux (i,i,i,o)


intercalare([],E,N,[E]):- N>0,!.
intercalare([],_,_,[]):-!.
intercalare([H|T],E,1,R):- intercalare(T,E,0,R1),R=[E,H|R1],!.
intercalare([H|T],E,N,R):- N1 is N-1, intercalare(T,E,N1,R1),R=[H|R1].

% ************************************************************************
% b)Definiti un predicat care intoarce cel mai mare divizor comun a;
% numerelor dintr-o lista.

%div(A:elem,B:elem,D:elem)
%A - primul numar
%B - al 2 lea numar
%D - cmmdc al numerelor A si B
%model de flux (i,i,o)

div(A,0,A):-!.
div(A,B,D):- C is mod(A,B),div(B,C,D).


% cmmdc(L:list,D:elem)
% L - lista data
% D - cmmdc al elementelor listei
% model de flux (i,o)

cmmdc([D],D):-!.
cmmdc([H|T],D):- cmmdc(T,R),div(H,R,D).
