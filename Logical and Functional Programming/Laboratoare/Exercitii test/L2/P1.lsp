;1. Se da un arbore de tipul (1). Sa se afiseze calea de la radacina pana la un nod x dat.


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


(defun cale(l x)
    (cond
        ((equal x (car l)) (list x))
        ((apare x (car (detSubarbori (cddr l) 0 0 nil))) (cons (car l) (cale (car (detSubarbori (cddr l) 0 0 nil)) x)))
        ((apare x (cadr (detSubarbori (cddr l) 0 0 nil))) (cons (car l) (cale (cadr (detSubarbori (cddr l) 0 0 nil))x)))
    )
)