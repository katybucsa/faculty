(defun subs(l e k)
    (cond
        ((and (atom l)(oddp k)) e)
        ((atom l) l)
        (t (mapcar #'(lambda(lst) 
                        (subs lst e (+ k 1))
                     )
                     l))
    )
)

(defun sub(l e)
    (subs l e -1)
)

;5--------------------------------------------------
(defun detMax(l m)
    (cond 
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (> (car l) m))) (detMax (cdr l) (car l)))
        (t (detMax (cdr l) m))
    )
)

(defun verif(l)
    (cond
        ((null l) t)
        ((atom (car l)) (verif (cdr l)))
        (t (and (not(null(detMax (car l) nil)))(evenp (detMax(car l) nil)) (verif  (car l))(verif (cdr l))))
    )
)

(defun numara(l)
    (cond
        ((atom l) nil)
        ((verif (list l)) (apply #'append (list l) (mapcar #'numara l)))
        (t (apply #'append (mapcar #'numara l)))
    )
)

;(A(B(F)(G(H(I)(J))))(C)(D(K)(L(N)(O))(M))(E(P(R(S(Z))(T)(V)))))
;(A(B 1)(1 C 4) 7 (D 1(6 F))((G 4)6)) 
;(A(B 2)(1 C 4) (D 1(9 F))((G 7)6)) 
;(A(B 2)(1 C 4) (D 1(5 F))((G 4)6)) 
;(1 A(B 2)(1 C 4) (D 1(6 F))((G 4)6)) 



