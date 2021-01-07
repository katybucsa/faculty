%a)Sa se sorteze o lista prin eliminarea dublurilor. De ex: [4 2 6 2 3
% 4] => [2 3 4 6].


%detMin(L:list,M:int)
%L - lista data
%M - elementul minim din lista L
%model de flux (i,o)

detMin([E],E):-!.
detMin([H|T],H):-detMin(T,M1),H<M1,!.
detMin([_|T],M):-detMin(T,M).

%elMin(L;list,M:elem,R:list)
%L - lista din care se vor elimina apritiile elementului M
%M - elementul care ca fi eliminat din lista L
%R - lista dupa eliminarea aparitiilor elementului M
%model de flux (i,i,o)

elMin([],_,[]):-!.
elMin([H|T],M,R):- H\=M,!,elMin(T,M,R1),R=[H|R1].
elMin([_|T],M,R):-elMin(T,M,R).

%sort(L:list,R:list)
%L - lista data
%R - lista sortata cu eliminarea dublurilor
%model deflux (i,o)

sorts([],[]):-!.
sorts(L,R):- detMin(L,M),
            elMin(L,M,L1),
            sorts(L1,R1),
            R=[M|R1].









