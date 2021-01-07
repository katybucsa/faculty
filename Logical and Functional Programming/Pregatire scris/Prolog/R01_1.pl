lung([],0):-!.
lung([_|T],R):-lung(T,R1),R is R1+1.

elimina([],_,[]):-!.
elimina([H|T],N,R):-is_list(H),lung(H,R1),mod(R1,2)=:=0,N>0,!,
                    N1 is N-1,elimina(T,N1,R).
elimina([H|T],N,[H|R]):-elimina(T,N,R).


subm([],0,[]).
subm([E|_],E,[E]).
subm([_|T],S,R):-subm(T,S,R).
subm([H|T],S,[H|R]):-S>H,S1 is S-H,subm(T,S1,R).


toate(L,S,R):-findall(R1,subm(L,S,R1),R).
