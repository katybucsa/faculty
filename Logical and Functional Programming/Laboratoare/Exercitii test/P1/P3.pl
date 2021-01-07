%a) Sa se scrie un predicat care transforma o lista intr-o multime, in
% ordinea primei aparitii. Exemplu: [1,2,3,1,2] e transformat in[1,2,3].

%apare(E:elem,L:list)
%E - elementul cautat
%L - lista in care se cauta
%model de flux (i,i)

apare(E,[E|_]):-!.
apare(E,[_|T]):-apare(E,T).

%adaugaSfarsit(E:elem,L:list,R:list)
%E - elementul care se va adauga
%L - lista in care se va adauga
%R - lista rezultata in urma inserarii elementului E la sfarsitul
%listei L
%model de flux(i,i,o)
adaugaSfarsit(E,[],[E]):-!.
adaugaSfarsit(E,[H|T],R):-adaugaSfarsit(E,T,R1),R=[H|R1].


%multime1(L:list,C:list,Col:list,R:list)
%L - lista data
%C - copia listei date
%Col - variabila colectoare
%R - multimea rezultata
%model de flux (i,i,i,o)

multime1([],R,R):-!.
multime1([H|T],Col,R):- \+apare(H,Col),
                         !,
                         adaugaSfarsit(H,Col,Rez),
                         multime1(T,Rez,R).
multime1([_|T],Col,R):- multime1(T,Col,R).

%multime(L:list,R:list)
%L - lista data
%R - multimea rezultata prin transformarea listei L
%model de flux (i,o)
multime(L,R):-multime1(L,[],R).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% b)Sa se scrie o functie care descompune o lista de numere intr-o lista
% de forma [lista-de-numere-pare lista-de-numere-impare](deci lista cu
% doua elemente care sunt liste de intregi),si va intoarce si numarul
% elementelor pare si impare


%descompunere(L:list,P:list,I:list,Np:elem,Ni:elem,R:list)
%L - lsita initiala
%P - lista cu numere pare
%I - lista cu numere impare
%Np - numarul de numere pare din lista P
%Ni - numarul de numere impare din lista I
%R - lista rezultat -[P,I]
%model de flux (i,o,o,o,o)
descompunere1([],[],[],0,0):-!.
descompunere1([H|T],P,I,Np,Ni):- mod(H,2)=:=0,!,
                                 descompunere1(T,P1,I,Np1,Ni),
                                 Np is Np1+1,
                                 P=[H|P1].
descompunere1([H|T],P,I,Np,Ni):- descompunere1(T,P,I1,Np,Ni1),
                                 Ni is Ni1+1,
                                 I=[H|I1].

%descompunere(L:list,R:list)
%L - lista data
%R - lista rezultat
%model de flux (i,o)
descompunere(L,R,Np,Ni):-descompunere1(L,P,I,Np,Ni),R=[P,I].












