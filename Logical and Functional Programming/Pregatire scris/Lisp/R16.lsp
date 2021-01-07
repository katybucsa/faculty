(defun suma(l niv)
    (cond
        ((and (numberp l) (oddp niv)) l)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda(lst)
                                    (suma lst (+ niv 1))
                                )
                                l)))
    )
)

(defun verif(l)
    (evenp (suma l 0))
)



(defun num(l)
    (cond
        ((atom l) nil)
        ((verif l) (apply #'append (list l) (mapcar #'num l)))
        (t (apply #'append (mapcar #'num l)))
    )
)

(defun nrAtomi(l niv)
    (cond
        ((and (numberp l) (evenp niv)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda(lst)
                                    (nrAtomi lst (+ niv 1))
                                )
                                l)))
    )
)

(defun verif1(l)
    (oddp (nrAtomi l 0))
)



(defun num1(l)
    (cond
        ((atom l) nil)
        ((verif1 l) (apply #'append (list l) (mapcar #'num1 l)))
        (t (apply #'append (mapcar #'num1 l)))
    )
)