% a) Se da o lista de numere intregi. Se cere sa se scrie de 2 ori in
% lista fiecare numar prim.

%prim(N:number,D:number)
%N - numarul de verificat
%D - divizor
%model de flux (i,i)

prim(N,D):- N>1,D>N/2,!.
prim(N,D):- N>1,mod(N,D)=\=0,D1 is D+1,prim(N,D1).


%scrie2Prim(L:list,R:list)
%L - lista data
%R - lista in care s-au scris de 2 ori numerele prime
%model de flux (i,o)

scrie2Prim([],[]):-!.
scrie2Prim([H|T],R):- prim(H,2),!,scrie2Prim(T,R1),R=[H,H|R1].
scrie2Prim([H|T],R):- scrie2Prim(T,R1),R=[H|R1].
