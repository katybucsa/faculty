(defun inloc(l k e)
    (cond
        ((and (atom l) (= k -1)) e)
        ((atom l) l)
        (t (mapcar #'(lambda(lst)
                        (inloc lst (- k 1) e)
                      )
                     l))
    )
)


(defun trans(l)
    (cond   
        ((numberp l) (list l))
        ((atom l) nil)
        (t  (apply #'append (mapcar #'trans l)))
    )
)

(defun verif(l)
    (cond   
        ((evenp (car (trans l))) t)
    )
)


(defun nrsubl(l)
    (cond
        ((not(listp l)) nil)
        ((verif l) (apply #'append (list l) (mapcar #'nrsubl l)))
        (t (apply #'append (mapcar #'nrsubl l)))
        ;((verif l) (apply #'+ 1 (mapcar #'nrsubl l)))
        ;(t (apply #'+ (mapcar #'nrsubl l)))
    )
)