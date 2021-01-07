divizori(R,1,_,R):-!.
divizori(L,D,N,R):- N mod D=:=0,!, L1=[D|L], D1 is D-1, divizori(L1,D1,N,R).
divizori(L,D,N,R):- D1 is D-1, divizori(L,D1,N,R).


listadiv([],[]).
listadiv([H|T],R):-listadiv(T,R1),H1 is H-1,divizori(R1,H1,H,R2),R=[H|R2].
