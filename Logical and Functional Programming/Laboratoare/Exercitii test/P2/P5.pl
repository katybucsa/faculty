%a)Sa se determine pozitiile elementului maxim dintr-o lista liniara. De
% ex: poz([10,14,12,13,14], L) va produce L = [2,5].

% detMax(L:list,M:elem)
% L - lista pentrucare se determina elementul maxim
% M - elementul maxim din lista L
% model de flux (i,o)

detMax([M],M):-!.
detMax([H|T],M):- detMax(T,M1),M1<H,!,M=H.
detMax([_|T],M):- detMax(T,M).

%poz1(L:list,M:elem,N:int,R:list)
%L - lista data
%M - elementul maxim din lista L
%N - pozitia unui element din lista
%R - lista pozitiilor elementului maxim
%model de flux (i,i,i,o)

poz1([],_,_,[]):-!.
poz1([M|T],M,N,R):- N1 is N+1,poz1(T,M,N1,R1),R=[N|R1],!.
poz1([_|T],M,N,R):- N1 is N+1,poz1(T,M,N1,R).


%poz(L:list,R:list)
%L - lista data
%R - lista pozitiilor elementului maxim
%model de flux (i,o)

poz([],[]):-!.
poz(L,R):-detMax(L,M),poz1(L,M,1,R).
