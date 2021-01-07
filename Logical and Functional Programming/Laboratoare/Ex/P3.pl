desc([],P,I,Np,Ni,[P,I]):-write("Elemente pare: "),
                          write(Np),nl,
                          write("Elemente impare:"),
                          write(Ni),!.
desc([H|T],P,I,Np,Ni,R):- mod(H,2)=:=0,!,
                          Np1 is Np+1,
                          P1=[H|P],
                          desc(T,P1,I,Np1,Ni,R).
desc([H|T],P,I,Np,Ni,R):- Ni1 is Ni+1,
                          I1=[H|I],
                          desc(T,P,I1,Np,Ni1,R).


descomp(L,R):- desc(L,[],[],0,0,R).
