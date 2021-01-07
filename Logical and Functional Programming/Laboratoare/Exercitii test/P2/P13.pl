% a) Sa se adauge dupa fiecare element dintr-o lista divizorii elementului.


%divizori(L:list,D:int,N:int,R:list)
%L - lista in care se vor adauga divizorii
%D - divizor
%N - numarul pentru care se cauta divizorii
%R - lista rezultata in urma inserarii divizorilor
%model de flux (i,i,i,o)


divizori(R,1,_,R):-!.
divizori(L,D,N,R):- N mod D=:=0,!, L1=[D|L], D1 is D-1, divizori(L1,D1,N,R).
divizori(L,D,N,R):- D1 is D-1, divizori(L,D1,N,R).


%listadiv(L:list,R:list)
%L - lista data
%R - lista in care dupa fiecare element au fost adaugati divizorii sai
% proprii
%model de flux (i,o)

listadiv([],[]).
listadiv([H|T],R):-listadiv(T,R1),H1 is H-1,divizori(R1,H1,H,R2),R=[H|R2].
