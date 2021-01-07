%a)Definiti un predicat care determina suma a doua numere scrise in
% reprezentare de lista.


%inverseaza(L:list,C:list,R:list)
%L - lista data
%C - colectoare
%R - lista L inversata
%model de flux (i,i,o)

inverseaza([],R,R):-!.
inverseaza([H|T],C,R):- C1=[H|C],inverseaza(T,C1,R).


%suma1(L:list,Y:list,P:int,C:int,S:int)
%L - prima lista data
%Y - a 2 a lista data
%P - pozitia unui  element
%C - suma partiala(colectoare)
%S - suma celor doua numere reprezentate sub forma de lista
%model de flux (i,i,i,i,o)

suma1([],[],_,S,S):-!.
suma1([],[H|T],P,C,S):- pow(10,P,X),
                        C1 is H*X+C,
                        P1 is P+1, suma1([],T,P1,C1,S),!.
suma1([H|T],[],P,C,S):- pow(10,P,X),
                        C1 is H*X+C,
                        P1 is P+1, suma1(T,[],P1,C1,S),!.
suma1([H1|T1],[H2|T2],P,C,S):- pow(10,P,X),
                               H is H1+H2,
                               C1 is H*X+C, P1 is P+1,
                               suma1(T1,T2,P1,C1,S).


suma2([],[],I,C,R):-I>0,R=[I|C],!.
suma2([],[],_,R,R):-!.
suma2([],[H|T],I,C,R):- X is H+I,
                        Z is div(X,10),
                        Y is mod(X,10),
                        C1=[Y|C],
                        suma2([],T,Z,C1,R),!.
suma2([H|T],[],I,C,R):- X is H+I,
                        Z is div(X,10),
                        Y is mod(X,10),
                        C1=[Y|C],
                        suma2(T,[],Z,C1,R),!.
suma2([H1|T1],[H2|T2],I,C,R):- X is H1+H2+I,
                             Z is div(X,10),
                             Y is mod(X,10),
                             C1=[Y|C],
                             suma2(T1,T2,Z,C1,R),!.


%suma(A:list,B:list,S:int)
%A - prima lista
%B - a 2 a lista
%S - suma celor doua numere
%model de flux (i,i,o)

suma(A,B,R):- inverseaza(A,[],A1),inverseaza(B,[],B1),suma2(A1,B1,0,[],R).
% suma(A,B,S):-inverseaza(A,[],A1),inverseaza(B,[],B1),suma1(A1,B1,0,0,S).
%












