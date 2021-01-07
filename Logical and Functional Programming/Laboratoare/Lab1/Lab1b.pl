% creare(M,N,R)
% M – capatul inferior al intervalului
% N – capatul superior al intervalului
% R – lista rezultat
% model de flux (i, i, o)


creare(M,N,R):- M=<N,I is M+1,creare(I,N,R1),R=[M|R1].
creare(M,N,[]):-M>N.
