(defun detMax(l m)
    (cond 
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (> (car l) m))) (detMax (cdr l) (car l)))
        ((atom (car l)) (detMax (cdr l) m))
        (t (detMax (cdr l) (detMax (car l) m)))
    )
)

(defun verif(l)
    (and(not(null(detMax l nil)))(evenp (detMax l nil)))
)
        

(defun numara(l)
    (cond   
        ;((atom l) 0)
        ((atom l) nil)
        ;((verif l) (apply #'+ 1 (mapcar #'numara l)))
        ((verif l) (apply #'append (list l) (mapcar #'numara l)))
        (t (apply #'append (mapcar #'numara l)))
        ;(t (apply #'+ (mapcar #'numara l)))
    )
)