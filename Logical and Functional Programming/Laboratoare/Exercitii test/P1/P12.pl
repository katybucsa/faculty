%a)Sa se scrie un predicat care substituie intr-o lista un element prin
% altul.

%substituie(L:list,E:elem,S:elem,R:list)
%L - lista data
%E - elementul care va fi substituit
%S - elementul cu care va fi substituit elementul E
%R - lista in urma substituirii elementului E cu elementul S
%model de flux (i,i,i,o)

substituie([],_,_,[]):-!.
substituie([E|T],E,S,R):-substituie(T,E,S,R1),R=[S|R1],!.
substituie([H|T],E,S,R):-substituie(T,E,S,R1),R=[H|R1].

% ************************************************************************
% b)Sa se construiasca sublista (lm,...,ln) a listei
% (l1,...,lk).(1<=m<=n<=k)

%sublista1(L:list,M:int,N:int,P:int,R:list)
%L - lista data
%M - pozitia de unde incepe sublista
%N - pozitia unde se incheie sublista
%P - pozitia in lista la un moment dat
%R - sublista rezultata
%model de flux (i,i,i,i,o)

sublista1([],_,N,P,[]):-P=<N,!.
sublista1([H|_],_,N,N,[H]):-!.
sublista1([_|T],M,N,P,R):-P<M,!,P1 is P+1,sublista1(T,M,N,P1,R).
sublista1([H|T],M,N,P,R):-P<N,P1 is P+1, sublista1(T,M,N,P1,R1),R=[H|R1].

%sublista(L:list,M:int,N:int,R:list)
%L - lista data
%M - pozitia de unde incepe sublista
%N - pozitia unde se incheie sublista
%R - sublista rezultata
%model de flux (i,i,i,o)

sublista(_,M,N,[]):-M>N,!.
sublista(L,M,N,R):-sublista1(L,M,N,1,R).












