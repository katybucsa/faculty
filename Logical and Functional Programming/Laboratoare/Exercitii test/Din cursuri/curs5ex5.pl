%Sa se concateneze 2 liste.

concatenare([],R,R).
concatenare([H|T],L,[H|R]):-concatenare(T,L,R).
