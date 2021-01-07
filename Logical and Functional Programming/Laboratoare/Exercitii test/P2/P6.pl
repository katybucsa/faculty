%a) Intr-o lista L sa se inlocuiasca toate aparitiile unui element E cu
%elementele unei alte liste, L1. De ex: inloc([1,2,1,3,1,4],1,[10,11],X)
%va produce X=[10,11,2,10,11,3,10,11,4].


%adaugaL(L:list,X:list,R:list)
%L - lista rezultat din functia inloc la un moment dat
%X - lista a carei elemente trebuie adaugate in lista L
%R - lista rezultata in urma adaugarii elementelor listei L
%model de flux (i,i,o)

adaugaL([],[],[]):-!.
adaugaL([],[H|T],R):-adaugaL([],T,R1),R=[H|R1],!.
adaugaL([H|T],[],R):-adaugaL(T,[],R1),R=[H|R1],!.
adaugaL(L,[H|T],R):-adaugaL(L,T,R1),R=[H|R1],!.



%inloc(L:list,E:elem,X:list,R:list)
%L - lista data
%E - elementul care va fi inlocuit
%X - lista cu care va fi inlocuit elementul E
%R - lista razultata in urma inlocuirilor
%model de flux (i,i,i,o)

inloc([],_,_,[]):-!.
inloc([E|T],E,X,R):-inloc(T,E,X,R1),adaugaL(R1,X,R),!.
inloc([H|T],E,X,R):-inloc(T,E,X,R1),R=[H|R1].
