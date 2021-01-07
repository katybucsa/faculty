(defun inloc(l e)
    (cond
        ((null l) nil)
        ((atom (car l)) (lambda(x)
                            (cond
                                ((numberp (car l)) (cons e x))
                                (t (cons (car l) x))
                            )
                        )
                        (inloc (cdr l) e))
        (t (cons (inloc (car l) e) (inloc (cdr l) e)))
    )
)

(defun inloc2(l e)
    (cond
        ((numberp l) e)
        ((atom l) l)
        (t (mapcar #'(lambda(lst)
                        (inloc2 lst e)
                     )
                     l))
    )
)

;5----------------------------------


(defun transf(l)
    (cond
        ((atom l) (list l))
        (t (apply #'append (mapcar #'transf l)))
    )
)

(defun verif(l)
    (and(not(null(transf l))) (not(numberp(car (transf l)))))
)

(defun numara(l)
    (cond
        ((atom l) nil)
        ((verif (list l)) (apply #'append (list l) (mapcar #'numara l)))
        (t (apply #'append (mapcar #'numara l)))
    )
)