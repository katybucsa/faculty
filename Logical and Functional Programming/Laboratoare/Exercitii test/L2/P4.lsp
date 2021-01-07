;4. Sa se converteasca un arbore de tipul (2) la un arbore de tipul (1).

(defun convert(l)
    (cond
        ((null l) nil)
        (t (append (list (car l) (- (length l) 1)) (convert (cadr l)) (convert (caddr l))))
    )
)