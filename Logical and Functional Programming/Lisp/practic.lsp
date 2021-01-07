(defun membru(l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (t (membru (cdr l) e))
    )
)


(defun numere(l c)
    (cond
        ((null l) c)
        ((and (numberp (car l)) (not(membru c (car l)))) (numere (cdr l) (append c (list(car l)))))
        ((atom (car l)) (numere (cdr l) c))
        (t (numere (cdr l) (numere (car l) c)))
    )
)

(defun atomiNr(l)
    (numere l nil)
)
