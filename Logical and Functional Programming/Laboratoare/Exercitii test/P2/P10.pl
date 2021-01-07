% a) Se da o lista de numere intregi. Se cere sa se adauge in lista dupa
% 1-ul element, al 3-lea element, al 7-lea elemen, al 15-lea element � o
% valoare data e.


%add2(L:list,E:elem,I:int,P:int,R:list)
%L - lista data
%E - elementul dat
%I - variabila
%P - pozitia unui element in lista
%R - lista dupa adaugarea elementului E
%model de flux (i,i,i,i,o)

add2([],_,_,_,[]):-!.
add2([H|T],E,I,P,R):- P=:=I,!,I1 is 2*I+1,P1 is P+1,add2(T,E,I1,P1,R1),R=[H,E|R1].
add2([H|T],E,I,P,R):- P1 is P+1, add2(T,E,I,P1,R1),R=[H|R1].

%adauga2(L:list,E:elem,R:list)
%L - lista data
%E - elementul dat
%R - lista dupa adaugarea elementului E
%model de flux (i,i,o)


adauga2(L,E,R):-add2(L,E,1,1,R).
