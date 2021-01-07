% eMembru(E-elem,L-list)
% E- elementul căutat
% L – lista în care se face căutarea
% model de flux (i, i)


eMembru(E,[E|_]):-!.
eMembru(E,[_|T]):- eMembru(E,T).



% intersectie(L: list, K: list, C:list, R:list)
% L – prima listă dată
% K – a 2-a listă dată
% C – variabilă colectoare
% R – lista rezultată prin intersecția lui L cu K
% model de flux (i, i, i, o)


intersectie([],_,R,R):-!.
intersectie([H|T],K,C,R):-eMembru(H,K),C1=[H|C], intersectie(T,K,C1,R),!.
intersectie([_|T],K,C,R):-intersectie(T,K,C,R).

intersectie2(L1,L2,R):-intersectie(L1,L2,[],R).

