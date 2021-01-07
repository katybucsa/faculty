;14. Sa se construiasca lista nodurilor unui arbore de tipul (2) parcurs in postordine.

(defun postordine(l)
    (cond
        ((null l) nil)
        (t (append (postordine (cadr l)) (postordine (caddr l)) (list (car l))))
    )
)