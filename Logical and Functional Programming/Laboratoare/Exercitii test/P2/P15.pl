% a) Sa se determine cea mai lunga secventa de numere pare consecutive
% dintr-o lista (daca sunt mai multe secvente de lungime maxima, una
% dintre ele).


%inverseaza(L:list,C:list,R:list)
%L - lista data
%C - colectoare
%R - lista L inversata
%model de flux (i,i,o)

inverseaza([],R,R):-!.
inverseaza([H|T],C,R):- C1=[H|C],inverseaza(T,C1,R).

%sP(L:list,X:int,M:int,C:list,Cm:list,R:list)
%L - lista data
%X - lungimea unei secvente consecutive de numere pare
%M - lungimea maxima a secventei de numere pare consecutive
%C - colectoare
%Cm - colectoare
%R - lista cele mai lungi secvente de numere pare consecutive
%model de flux (i,i,i,i,i,o)

sP([],X,M,R,_,R):- X>M,!.
sP([],_,_,_,R,R):-!.
sP([H|T],X,M,C,Cm,R):- mod(H,2)=:=0,!,X1 is X+1,C1=[H|C],sP(T,X1,M,C1,Cm,R).
sP([_|T],X,M,C,_,R):- X>M,!,sP(T,0,X,[],C,R).
sP([_|T],_,M,_,Cm,R):- sP(T,0,M,[],Cm,R).


%secPara(L:list,R:list)
%L - lista data
% R - lista celei mai lungi secvente de numere pare consecutive din
% lista L
% model de flux (i,o)

secPara(L,R):- sP(L,0,0,[],[],R1),inverseaza(R1,[],R).



