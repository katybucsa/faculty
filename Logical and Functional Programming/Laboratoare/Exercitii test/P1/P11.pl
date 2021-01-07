%a)Sa se scrie un predicat care sa testeze daca o lista formata din
% numere intregi are aspect de "vale"(o multime se spune ca are aspect
% de "vale" dace elementele descresc pana la un moment dat, apoi cresc.
% De ex: 10, 8, 6, 11,13).

%testV(L:list,N:int)
% L - lista daca care contine cel putin 3 elemente si incepe cu 2 numere
% in ordice strict crescatoare
% N - numarul de secvente descrescatoare urmate de secvente crescatoare
% model de flux (i,i)

testV([_,_],1):-!.
testV([X,Y,Z|T],N):- X>Y,Y<Z,!,N1 is N+1,testV([Y,Z|T],N1).
testV([X,Y,Z|T],N):- X<Y , Y<Z,!,testV([Y,Z|T],N).
testV([X,Y,Z|T],N):- X>Y, Y>Z, testV([Y,Z|T],N).


% vale(L:list)
% L - lista data
% model de flux (i)

vale([X,Y,Z|T]):- X>Y,testV([X,Y,Z|T],0).

% ************************************************************************
% b)Sa se calculeze suma alternanta a elementelor unei liste (11 - 12 +
% 13).

%suma(L:list,P:number,C:number,S:number)
%L - lista data care contine cel putin un element
% P - variabila aditionala, valoarea ei indicand operatia care se va
% executa( -,+) la fiecare pas
% C - variabila colectoare pentru suma
% S - suma alternantaa elementelor listei L
% model de flux (i,i,i,o)

suma([],_,S,S):-!.
suma([H|T],N,C,S):- mod(N,2) =\= 0,!,C1 is C-H,N1 is N+1,suma(T,N1,C1,S).
suma([H|T],N,C,S):- C1 is C+H,N1 is N+1,suma(T,N1,C1,S).

%sumaAlternanta(L:list,S:int)
%L - lista data
%S - suma alternanta a elementelor listei
%model de flux (i,o)

sumaAlternanta([],0):-!.
sumaAlternanta([H|T],S):- suma(T,1,H,S).
