;6. Sa se construiasca o functie care intoarce produsul atomilor numerici dintr-o lista, de la orice nivel.

(defun produs(l)
    (cond
        ((numberp l) l)
        ((atom l) 1)
        (t (apply #'* (mapcar #'produs l)))
    )
)