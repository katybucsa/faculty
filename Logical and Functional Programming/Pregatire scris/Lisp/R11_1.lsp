(defun apare(e l)
    (cond
        ((null l) nil)
        ((listp (car l)) (or (apare e (car l)) (apare e (cdr l))))
        ((equal e (car l)) t)
        (t (apare e (cdr l)))
    )
)

(defun cale(l x)
    (cond   
        ((atom l) (list l))
        ((not(apare x l)) nil)
        (t (apply #'append (mapcar #'(lambda(lst)
                                        (cale lst x)
                                     )
                                     l)))
    )
)