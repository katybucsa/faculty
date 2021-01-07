(defun nrniv(l niv)
    (cond
        ((null l) niv)
        ((atom (car l)) (nrniv (cdr l) niv))
        (t (max (nrniv (car l) (+ niv 1)) (nrniv (cdr l) niv)))
    )
)

(defun verif(l)
    (evenp (nrniv l 0))
)



(defun num(l)
    (cond
        ((atom l) nil)
        ((verif (list l)) (apply #'append (list l) (mapcar #'num l)))
        (t (apply #'append (mapcar #'num l)))
    )
)


(defun substl (l e l1)
(cond
	((equal l e) l1)
	((atom l) (list l))
	(t (list (apply #'append (mapcar (lambda(l) (substl l e l1)) l))))
)
)

(defun el(l e)
    (cond
        ((and (atom l) (equal e l)) nil)
        ((atom l) (list l))
        (t (list(apply #'append (mapcar #'(lambda(lst)
                                                (el lst e)
                                           )
                                           l))))
    )
)    
    
(defun elm(l e)
    (car (el l e))
)
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     