%a)Sa se scrie un predicat care substituie intr-o lista un element cu o
% alta lista.
% substituie(L:list,E:elem,Ld:list,R:list)
% L - lista data
% E - elementul care va fi substituit
% Ld - lista cu care va fi substituit elementul E
% R - lista rezultata
% model de flux (i,i,i,o)


substituie([],_,_,[]):-!.
substituie([H|T],E,Ld,R):- H=:=E,!,substituie(T,E,Ld,R1),R=[Ld|R1].
substituie([H|T],E,Ld,R):-substituie(T,E,Ld,R1),R=[H|R1].


% &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
%b) Sa se elimine elementul de pe pozitia a n-a a unei liste liniare
%elimina(L:list,N:elem,R:list)
%L - lista data
%N - pozitia
%R - lista dupa eliminarea elementului de pe pozitia N
%model de flux (i,i,o)

elimina([],_,[]):-!.
elimina([_|T],N,R):- N=1,!,elimina(T,0,R).
elimina([H|T],N,R):- N1 is N-1, elimina(T,N1,R1),R=[H|R1].
