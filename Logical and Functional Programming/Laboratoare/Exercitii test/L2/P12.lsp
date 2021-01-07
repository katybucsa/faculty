;12. Sa se construiasca lista nodurilor unui arbore de tipul (2) parcurs in preordine.

(defun preordine(l)
    (cond 
        ((null l) nil)
        (t (append (list (car l)) (preordine (cadr l)) (preordine (caddr l)) ))
    )
)  