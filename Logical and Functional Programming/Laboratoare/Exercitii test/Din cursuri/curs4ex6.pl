submultimi([],[]).
submultimi([_|T],R):-submultimi(T,R).
submultimi([H|T],[H|R]):-submultimi(T,R).

subm(L,R):-findall(R1,submultimi(L,R1),R).
