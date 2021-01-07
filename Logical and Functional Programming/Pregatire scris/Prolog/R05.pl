prim(N,D):-D>N/2,N>1,!.
prim(N,D):-N>1,mod(N,D)=\=0,D1 is D+1,prim(N,D1).

elim([],_,[]):-!.
elim(L,0,L):-!.
elim([H|T],N,R):-prim(H,2),!,N1 is N-1,elim(T,N1,R).
elim([H|T],N,[H|R]):-elim(T,N,R).


submultimi([E|_],E,[E]).
submultimi([_|T],S,R):-submultimi(T,S,R).
submultimi([H|T],S,[H|R]):-S>H,S1 is S-H,submultimi(T,S1,R).


subm(L,S,R):-findall(X,submultimi(L,S,X),R).




