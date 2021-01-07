;9. Sa se converteasca un arbore de tipul (1) la un arbore de tipul (2).


(defun detSubarbori(l m v s)
    (cond 
        ((or (= v (+ m 1)) (null l)) (list s l))
        (t (detSubarbori (cddr l) (+ m (cadr l)) (+ v 1) (append s (list (car l) (cadr l)))))
    )
)

(defun convert(l)
    (cond
        ((null l) nil)
        ((and (not (null(convert (cadr (detSubarbori (cddr l) 0 0 nil))))) (not (null(convert (car (detSubarbori (cddr l) 0 0 nil)))))) (append (list (car l)) (cons (convert (car (detSubarbori (cddr l) 0 0 nil))) (list(convert (cadr (detSubarbori (cddr l) 0 0 nil)))))))
        ((not (null(convert (car (detSubarbori (cddr l) 0 0 nil))))) (cons (car l) (list(convert (car (detSubarbori (cddr l) 0 0 nil))))))
        (t (cons (car l) (convert (cadr (detSubarbori (cddr l) 0 0 nil)))))
    )
)