(defun inloc(l)
    (cond
        ((null l) nil)
        ((and (numberp (car l)) (evenp (car l))) (cons (+ (car l) 1) (inloc (cdr l))))
        ((listp (car l)) (append (list(inloc(car l))) (inloc (cdr l))))
        (t (cons (car l) (inloc(cdr l))))
    )
)

(defun inloc1(l)
    (cond   
        ((and (numberp l) (evenp l)) (+ l 1))
        ((listp l) (mapcar #'inloc1 l))
        (t l)
    )
)


(defun detMax(l m)
    (cond 
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (> (car l) m))) (detMax (cdr l) (car l)))
        (t (detMax (cdr l) m))
    )
)

(defun verif(l k)
    (cond
        ((null l) t)
        ((atom (car l)) (verif (cdr l) k))
        ((and (oddp k)  (not(null (detMax l nil))) (oddp (detMax l nil))) nil)
        (t (and (verif (car l) (+ k 1)) (verif (cdr l) k)))
    )
)
        

(defun numara(l k)
    (cond   
        ((atom l) nil)
        ((verif (list l) k) (apply #'append (list l) (mapcar #'(lambda(lst)
                                                (numara lst (+ k 1))
                                           )
                                           l)))
        (t (apply #'append (mapcar #'(lambda(lst)
                                                (numara lst (+ k 1))
                                           )
                                           l)))
    )
)


(defun F(l)
    (cond
        ((atom l) 1)
        (t (lambda(x y)
                (cond
                    ((> x 0) (+ (car l) x y))
                    (t y)
                )
            ) 
            (F(car l))
            (F(cdr l))
        )
    )
)
                
                
                
                
                
                
                
                
                
                
                
                