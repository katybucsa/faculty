%a)Sa se scrie un predicat care intoarce diferenta a doua multimi.

%apare(E:elem,L:list)
%E - elementul cautat
%L - lista in care se cauta
%model de flux (i,i)

apare(E,[E|_]):-!.
apare(E,[_|T]):-apare(E,T).

%diferenta(A:list, B:list, R:list)
%A - prima multime
%B - a 2-a multime
% R - multimea rezultat(diferenta A-B=multimea elementelor care sunt in
% A si nu sunt in B)
% model de flux (i,i,o)

diferenta([],_,[]):-!.
diferenta([H|T],B,R):- \+apare(H,B),!,diferenta(T,B,R1),R=[H|R1].
diferenta([_|T],B,R):-diferenta(T,B,R).

% b)Sa se scrie un predicat care adauga intr-o lista dupa fiecare
% element par valoarea 1
% adaugaUnu(L:list,R:list)
% L - lista data
% R - lista rezultat
% model de flux (i,o)

adaugaUnu([],[]):-!.
adaugaUnu([H|T],R):- H mod 2=:=0,!,adaugaUnu(T,R1),R=[H,1|R1].
adaugaUnu([H|T],R):- adaugaUnu(T,R1), R=[H|R1].
