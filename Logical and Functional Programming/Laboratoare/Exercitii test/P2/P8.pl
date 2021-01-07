% a) Definiti un predicat care determina succesorul unui numar
% reprezentat cifra cu %cifra intr-o lista. De ex: [1 9 3 5 9 9] => [1 9
% 3 6 0 0].

%invers(L:list,C:list,R:ist)
%L - lista care se va inversa
%C - colectoare
%R - lista inversata
%model de flux (i,i,o)

invers([],R,R):-!.
invers([H|T],C,R):-C1=[H|C],invers(T,C1,R).

%succesor1(L:list,P:int,C:list,R:list)
%L - lista data
%P - numar care arata daca mai trebuie sa adun 1
%C - colectoare
%R - lista cu succesorul numarului
%model de flux (i,i,i,o)

succesor1([],P,R,[P|R]):- P=\=0,!.
succesor1([],_,R,R):-!.
succesor1([H|T],P,C,R):- H+P=:=10,C1=[0|C],succesor1(T,1,C1,R),!.
succesor1([H|T],P,C,R):- H1 is H+P,C1=[H1|C],succesor1(T,0,C1,R).


%succesor(L:list,R:list)
%L - lista data
%R - lista cu succesorul numarului
%model de flux (i,o)

succesor([],[]):-!.
succesor(L,R):-invers(L,[],R1),succesor1(R1,1,[],R).







