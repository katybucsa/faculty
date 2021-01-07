predicat(a, b).
predicat(c, d).
predicat(e, f).
toate :-
predicat(X, Y),
write(X),write(Y),nl,
fail.
toate1 :-
predicat(X, Y),
write(X),write(Y),nl.


sP([],[]).
sP([_|T],S):-sP(T,S).
sP([H|T],[H|S]):- H mod 2 =:=0,
                  !,
                  sP(T,S).
sP([H|T],[H|S]):-sI(T,S).

sI([H],[H]):-H mod 2 =\=0, !.
sI([_|T],S):-sI(T,S).
sI([H|T],[H|S]):-H mod 2 =:=0,
                 !,
                 sI(T,S).
sI([H|T],[H|S]):-sP(T,S).
