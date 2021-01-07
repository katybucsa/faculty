%a)Sa se scrie un predicat care transforma o lista intr-o multime, in
% ordinea ultimei aparitii.Exemplu:[1,2,3,1,2] e transformat in [3,1,2].

%multime(L:list,R:list)
%L - lista data
%R - multimea rezultata in ulma transformarii listei L
%model de flux (i,o)

multime([],[]):-!.
multime([H|T],R):-multime(T,R1),\+apare(H,R1),!,R=[H|R1].
multime([_|T],R):-multime(T,R).

% ************************************************************************
%b)Acelasi ex cu ===== > 10b)
