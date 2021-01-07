(defun ultim(l u)
    (cond
        ((null l) u)
        ((numberp (car l)) (ultim (cdr l) (car l)))
        (t (ultim (cdr l) u))
    )
)


(defun verif(l)
    (cond
        ((null l) t)
        ((atom (car l)) (verif (cdr l)))
        (t (and (not(null(ultim (car l) nil)))(oddp (ultim(car l) nil)) (verif (car l)) (verif (cdr l))))
    )
)

(defun nrsub10(l)
    (cond
        ((atom l) nil)
        ((verif (list l)) (apply #'append (list l) (mapcar #'nrsub10 l)))
        (t (apply #'append (mapcar #'nrsub10 l)))
    )
)