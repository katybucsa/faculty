%a) Sa se inlocuiasca toate aparitiile unui element dintr-o lista cu un alt element.

%inlocAp(L:list,E:elem,I:elem,R:list)
%L - lista data
%E - elementul care va fi inlocuit
%I - elementul cu care va fi inlocuit elementul E
%R - lista rezultata in urma inlocuirilor
%model de flux (i,i,i,o)

inlocAp([],_,_,[]):-!.
inlocAp([E|T],E,I,R):-inlocAp(T,E,I,R1),R=[I|R1],!.
inlocAp([H|T],E,I,R):-inlocAp(T,E,I,R1),R=[H|R1].
