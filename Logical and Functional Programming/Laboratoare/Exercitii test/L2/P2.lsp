;2. Sa se tipareasca lista nodurilor de pe nivelul k dintr-un arbore de tipul (1).

(defun detSubarbori(l m v s)
    (cond 
        ((or (= v (+ m 1)) (null l)) (list s l))
        (t (detSubarbori (cddr l) (+ m (cadr l)) (+ v 1) (append s (list (car l) (cadr l)))))
    )
)


(defun nivelk(l k niv)
    (cond
        ((null l) nil)
        ((= k niv) (list (car l)))
        (t (append (nivelk (car (detSubarbori (cddr l) 0 0 nil)) k (+ niv 1)) (nivelk (cadr (detSubarbori (cddr l) 0 0 nil)) k (+ niv 1))))
    )
)

(defun lniv(l k)
    (nivelk l k 0)
)