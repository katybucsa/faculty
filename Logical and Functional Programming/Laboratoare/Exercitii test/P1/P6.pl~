%a)Sa se scrie un predicat care elimina dintr-o lista toate elementele
% care se repeta (ex: l=[1,2,1,4,1,3,4] =>l=[2,3])

%aparitii(L:list,E:atom,R:elem)
%L - lista in care se cauta
%E - elementul cautat
%R - numarul de aparitii ale atomului E in lista L
%model de flux (i,i,o)
aparitii([],_,0):-!.
aparitii([E|T],E,R):-aparitii(T,E,R1),R is R1+1,!.
aparitii([_|T],E,R):-aparitii(T,E,R).


%elDup(L:list,C:list,R:list)
%L - lista data
%C - copie a listei date
%R - lista initiala din care s-au sters elementele duplicate
%model de flux (i,i,o)

elDup([],_,[]):-!.
elDup([H|T],C,R):- elDup(T,C,R1),aparitii(C,H,N),N<2,!,R=[H|R1].
elDup([_|T],C,R):- elDup(T,C,R).


% eliminaDuplicate(L:list,R:list)
% L - lista data
% R - lista din care s-au sters elementele duplicate
% model de flux (i,o)

eliminaDuplicate(L,R):-elDup(L,L,R).

% ************************************************************************
% b)Sa se elimine toate aparitiile elementului maxim dintr-o lista de
% numere intregi.
% detMax(L:list,M:elem)
% L - lista pentrucare se determina elementul maxim
% M - elementul maxim din lista L
% model de flux (i,o)

detMax([M],M):-!.
detMax([H|T],M):- detMax(T,M1),M1<H,!,M=H.
detMax([_|T],M):- detMax(T,M).


%elMax(L;list,M:elem,R:list)
%L - lista din care se vor elimina apritiile elementului M
%M - elementul care ca fi eliminat din lista L
%R - lista dupa eliminarea aparitiilor elementului M
%model de flux (i,i,o)
elMax([],_,[]):-!.
elMax([H|T],M,R):- H\=M,!,elMax(T,M,R1),R=[H|R1].
elMax([_|T],M,R):-elMax(T,M,R).


%eliminaMax(L:list,R:list)
% L - lista data
% R - lista initiala din care s-au eliminat toate aparitiile elementului
% maxim
% model de flux (i,o)

eliminaMax([],[]):-!.
eliminaMax([H|T],R):- detMax([H|T],M),elMax([H|T],M,R).
