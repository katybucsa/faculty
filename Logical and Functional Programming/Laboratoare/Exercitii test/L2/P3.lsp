;3. Se da un arbore de tipul (1). Sa se precizeze numarul de niveluri din arbore.

(defun detSubarbori(l m v s)
    (cond 
        ((or (= v (+ m 1)) (null l)) (list s l))
        (t (detSubarbori (cddr l) (+ m (cadr l)) (+ v 1) (append s (list (car l) (cadr l)))))
    )
)


(defun nrniv(l)
    (cond
        ((null l) 0)
        (t (max (+ 1 (nrniv (car (detSubarbori (cddr l) 0 0 nil)))) (+ 1 (nrniv (cadr (detSubarbori (cddr l) 0 0 nil))))))
    )
)