%a)Sa se scrie un predicat care sterge toate aparitiile unui anumit atom
% dintr-o lista.
% stergeAtom(L:list,A:atom,R:list)
% L - lista data
% A - atomul care trebuie sters
% R - lista L din care s-au sters aparitiile atomului A
% model de flux (i,i,o)

stergeAtom([],_,[]):-!.
stergeAtom([A|T],A,R):- stergeAtom(T,A,R),!.
stergeAtom([H|T],A,R):-stergeAtom(T,A,R1), R=[H|R1].


% ************************************************************************
% b)Definiti un predicat care, dintr-o lista de atomi, produce o lista
% de perechi (atom, n), unde atom apare in lista initiala de n ori. De
% ex: numar([1,2,1,2,1,3,1],X) va produce X = [[1,4],[2,2],[3,1]].

%aparitii(L:list,E:atom,R:elem)
%L - lista in care se cauta
%E - elementul cautat
%R - numarul de aparitii ale atomului E in lista L
%model de flux (i,i,o)
aparitii([],_,0):-!.
aparitii([E|T],E,R):-aparitii(T,E,R1),R is R1+1,!.
aparitii([_|T],E,R):-aparitii(T,E,R).


%numar1(L;list,C:list,X:list)
%L - lista data
%C - copie a listei date
%X - lista rezultat
%model de flux (i,i,o)

numar1([],_,[]):-!.
numar1([H|T],C,X):- aparitii(C,H,N),
                    stergeAtom([H|T],H,R1),
                    numar1(R1,C,X1),
                    X=[[H,N]|X1].



% numar(L:list,X:list)
% L - lista data
% X - lista in care elementele sunt perechi de forma (atom, nrAparitii)
% model de flux (i,o)

numar(L,X):-numar1(L,L,X).
