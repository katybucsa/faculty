;6. Sa se construiasca lista nodurilor unui arbore de tipul (1) parcurs in inordine.

(defun detSubarbori(l m v s)
    (cond 
        ((or (= v (+ m 1)) (null l)) (list s l))
        (t (detSubarbori (cddr l) (+ m (cadr l)) (+ v 1) (append s (list (car l) (cadr l)))))
    )
)


(defun inordine(l)
    (cond
        ((null l) nil)
        (t (append (inordine (car (detSubarbori (cddr l) 0 0 nil))) (list (car l)) (inordine(cadr (detSubarbori (cddr l) 0 0 nil)))))
    )
)