% a) Dandu-se o lista liniara numerica, sa se stearga toate secventele
% de valori consecutive. Ex: sterg([1, 2, 4, 6, 7, 8, 10], L) va produce
% L=[4, 10].


%stergeC(L:list,I:int,R:list)
%L - lista data
%I - variabila
%R - lista din care s-au sters valorile consecutive
%model de flux (i,i,o)

stergeC([H],0,[H]):-!.
stergeC([_],1,[]):-!.
stergeC([H1,H2|T],_,R):- H2=:=H1+1,!,stergeC([H2|T],1,R).
stergeC([H1,H2|T],0,R):- stergeC([H2|T],0,R1),R=[H1|R1].
stergeC([_,H2|T],1,R):- stergeC([H2|T],0,R).


%stergeConsec(L:list,R:list)
%L - lista data
%R - lista rezultata in urma stergerilor valorilor consecutive
%model de flux (i,o)

stergeConsec([H1,H2|T],R):-stergeC([H1,H2|T],0,R),!.
stergeConsec(L,L).



