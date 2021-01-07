%a) Sa se interclaseze fara pastrarea dublurilor doua liste sortate.

%elimina(L;list,M:elem,R:list)
%L - lista din care se vor elimina apritiile elementului M
%M - elementul care ca fi eliminat din lista L
%R - lista dupa eliminarea aparitiilor elementului M
%model de flux (i,i,o)

elimina([],_,[]):-!.
elimina([H|T],M,R):- H\=M,!,elimina(T,M,R1),R=[H|R1].
elimina([_|T],M,R):-elimina(T,M,R).




%intercalare(A:list,B:list,R:list)
%A - prima lista data
%B - a 2 a lista data
%R - lista rezultata prin intercalare
%model de flux (i,i,o)

intercalare([],[],[]):-!.
intercalare([H|T],[],R):- elimina(T,H,X),
                          intercalare(X,[],R1),
                          R=[H|R1],!.
intercalare([],[H|T],R):- elimina(T,H,X),
                          intercalare([],X,R1),
                          R=[H|R1],!.
intercalare([H1|T1],[H2|T2],R):-H1<H2,!,
                                elimina(T1,H1,Tr1),
                                elimina([H2|T2],H1,Tr2),
                                intercalare(Tr1,Tr2,R1),
                                R=[H1|R1].
intercalare([H1|T1],[H2|T2],R):- elimina([H1|T1],H2,Tr1),
                                 elimina(T2,H2,Tr2),
                                 intercalare(Tr1,Tr2,R1),
                                 R=[H2|R1].



