(defun nrnoduri(l k)
    (cond
        ((and (atom l) (= k 0)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda(lst)
                                    (nrnoduri lst (- k 1))
                                )
                                l)))
    )
)


(defun transf(l)
    (cond   
        ((numberp l) (list l))
        ((atom l) nil)
        (t (apply #'append (mapcar #'transf l)))
    )
)

(defun verif(l)
    (and (not(null(transf l))) (oddp (car (transf l))))
)

(defun nrsubImp(l)
    (cond
        ((atom l) 0)
        ((verif l) (apply #'+ 1 (mapcar #'nrsubImp l)))
        (t (apply #'+ (mapcar #'nrsubImp l)))
    )
)