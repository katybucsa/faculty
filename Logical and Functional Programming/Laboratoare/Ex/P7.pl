


addP(R,_,[],R):-!.
addP(L,E,[H|T],R):- L1=[[E,H]|L], addP(L1,E,T,R).


perechi([],[]):-!.
perechi([H|T],R):-perechi(T,R1),addP(R1,H,T,R).
