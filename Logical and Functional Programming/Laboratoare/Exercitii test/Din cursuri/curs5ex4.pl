%Sa se genereze permutarile unei multimi reprezentate sub forma de
% lista.

%sa se insereze un element pe toate pozitiile intr-o lista

inserare(L,E,[E|L]).
inserare([H|T],E,[H|R]):-inserare(T,E,R).


permutari([],[]).
permutari([H|T],R):-permutari(T,R1),inserare(R1,H,R).
