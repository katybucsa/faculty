;5. Sa se intoarca adancimea la care se afla un nod intr-un arbore de tipul (1).

(defun detSubarbori(l m v s)
    (cond 
        ((or (= v (+ m 1)) (null l)) (list s l))
        (t (detSubarbori (cddr l) (+ m (cadr l)) (+ v 1) (append s (list (car l) (cadr l)))))
    )
)

(defun apare(e l)
    (cond
        ((null l) nil)
        ((equal e (car l)) t)
        (t (apare e (cddr l)))
    )
)

(defun adancime(l x)
    (cond 
        ((null l) nil)
        ((equal x (car l)) 0)
        ((apare x (car (detSubarbori (cddr l) 0 0 nil))) (+ 1 (adancime (car (detSubarbori (cddr l) 0 0 nil)) x)))
        ((apare x (cadr (detSubarbori (cddr l) 0 0 nil))) (+ 1 (adancime (cadr (detSubarbori (cddr l) 0 0 nil)) x)))
    )
)
        