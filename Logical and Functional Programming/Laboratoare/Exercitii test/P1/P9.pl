%a)Sa se scrie un predicat care intoarce intersectia a doua multimi.
%apare(E:elem,L:list)
% E - elementul căutat
% L – lista în care se face căutarea
% model de flux (i, i)


apare(E,[E|_]):-!.
apare(E,[_|T]):- apare(E,T).



% intersectie(L: list, K: list, C:list, R:list)
% L – prima listă dată
% K – a 2-a listă dată
% C – variabilă colectoare
% R – lista rezultată prin intersecția lui L cu K
% model de flux (i, i, i, o)


intersectie([],_,[]):-!.
intersectie([H|T],K,R):- apare(H,K),!,intersectie(T,K,R1),R=[H|R1].
intersectie([_|T],K,R):-intersectie(T,K,R).


% ************************************************************************
% b) Sa se construiasca lista (m,...,n) adica multimea numerelor intregi
% din intervalul [m,n].

% creare(M,N,R)
% M – capatul inferior al intervalului
% N – capatul superior al intervalului
% R – lista rezultat
% model de flux (i, i, o)

creare(M,N,[]):-M>N,!.
creare(M,N,R):- I is M+1,creare(I,N,R1),R=[M|R1].








