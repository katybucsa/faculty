%candidat(N:int,R:int)
%N - numarul dat
%R - rezultat; un numar din intervalul [2,N]
%model de flux (i,o)-nedet

candidat(N,N).
candidat(N,R):-N>2,N1 is N-1,candidat(N1,R).

%apare(E:elem,L:list)
%E - elementul care se cauta
%L - lista in care se cauta
%model de flux (i,i)

apare(E,[E|_]):-!.
apare(E,[_|T]):-apare(E,T).

%ePrim(E:int,D:int)
%E - elementul care se verifica
%D - posibil divizor pentru E
%model de flux (i,i)

ePrim(E,D):- D>E/2,!.
ePrim(E,D):- E mod D=\=0, D1 is D+1, ePrim(E,D1).

%sumaNrPrime_aux(N:int,S:int,C:list,R:list)
%N - numarul dat
%S - suma elementelor din colectoare
%R - lista elementelor prime distincte a caror suma este N
%model de flux (i,i,i,o)-nedet

sumaNrPrime_aux(N,_,_,[]):-N<2,!.
sumaNrPrime_aux(N,N,R,R):-!.
sumaNrPrime_aux(N,S,[H|Col],R):-candidat(N,E),\+apare(E,Col),
                                ePrim(E,2),E<H,
                                S1 is S+E, S1=<N,
                                sumaNrPrime_aux(N,S1,[E|[H|Col]],R).

%sumaNrPrime(N:int,R:list)
%N - numarul dat
%R - lista elementelor prime distincte a caror suma este N
%model de flux (i,o)-nedet

sumaNrPrime(N,R):-candidat(N,E),ePrim(E,2),sumaNrPrime_aux(N,E,[E],R).




