sterg([],_,_,_,[]):-!.
sterg([_|T],Ps,Ps,I,R):-!,Pc is Ps+1,Ps1 is Ps+I,I1 is I+1,sterg(T,Pc,Ps1,I1,R).
sterg([H|T],Pc,Ps,I,[H|R]):-Pc1 is Pc+1,sterg(T,Pc1,Ps,I,R).


sterge(L,R):-sterg(L,1,1,1,R).


subm([E|_],[E]).
subm([_|T],R):-subm(T,R).
subm([H|T],[H|R]):-subm(T,R).


sumaEl([],0):-!.
sumaEl([H|T],S):-sumaEl(T,S1),S is S1+H.


sub(L,R):-subm(L,R),sumaEl(R,S),mod(S,3)=:=0.

toatesubm(L,R):-findall(X,sub(L,X),R).


candidat([E|_],E).
candidat([_|T],E):-candidat(T,E).


subm(L,K,R):-candidat(L,E1),candidat(L,E2),E1<E2,subm_aux(L,K,[E1,E2],2,R).

subm_aux(_,N,R,N,R).
subm_aux(L,K,[E1,E2|T],I,R):-candidat(L,E),E2-E1=:=E1-E,I1 is I+1,
                             subm_aux(L,K,[E,E1,E2|T],I1,R).

toate(L,K,R):-findall(X,subm(L,K,X),R).
