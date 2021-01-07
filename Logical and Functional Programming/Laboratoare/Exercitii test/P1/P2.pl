%a)Sa se scrie un predicat care determina cel mai mic multiplu comun al
% elementelor unei liste formate din numere intregi.

%cmmdc(A:elem,B:elem,D:elem)
%A - primul numar
%B - al 2-lea numar
%D - cel mai mare divizor comun al numerelor A si B
%model de flux (i,i,o)

cmmdc(A,0,A):-!.
cmmdc(A,B,R):-C is mod(A,B),cmmdc(B,C,R).

%cmmdc(A,A,A):-!.
%cmmdc(A,B,D):- A>B,!, A1 is A-B,cmmdc(A1,B,D).
%cmmdc(A,B,D):- B1 is B-A, cmmdc(A,B1,D).

%cmmmc1(L:list,M:elem)
%L - lista data
%M - cel mai mic multiplu comun al numerelor din lista
%model de flux (i,o)

cmmmc1([M],M):-!.
cmmmc1([H|T],M):- cmmmc1(T,M1),cmmdc(H,M1,D),M is (H*M1)/D.



% cmmmc(L:list,M:elem)
% L - lista data
% M - rezultatul: cel mai mic multiplu comun al elementelor listei L
% model de flux (i,o)

cmmmc([],-1):-write("Lista este vida"),!.
cmmmc(L,M):- cmmmc1(L,M).


% !!!!!!!!!!""""""""""����������$$$$$$$$$$%%%%%%%%%%^^^^^^^^^^&&&&&&&&&&


%b)Sa se scrie un predicat care adauga dupa 1-ul, al 2-lea,al 4-lea,
% al 8-lea...element al unei liste o valoare v data.

%putereDoi(P:elem)
%P - numarul care se verifica daca e putere a lui 2
%model de flux (i)
putereDoi(1):-!.
putereDoi(N):-N mod 2=:=0, N1 is N/2,putereDoi(N1).


%adauga1(L:list,V:elem,P:elem,R:list)
%L - lista data
%V -valoarea data
%P - pozitia unui element la un moment dat
%R -lista rezultat
adauga1([],_,_,[]):-!.
adauga1([H|T],V,P,R):- P1 is P+1,adauga1(T,V,P1,R1),putereDoi(P),!,R=[H,V|R1].
adauga1([H|T],V,P,R):- P1 is P+1,adauga1(T,V,P1,R1),R=[H|R1].


% adauga(L:list,V:elem,R:list)
% L - lista data
% V - valoarea care trebuie introdusa
% R -lista rezultata dupa introducerea valorii V
% model de flux (i,i,o)


adauga(L,V,R):-adauga1(L,V,1,R).











































































