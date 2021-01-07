;15. Sa se construiasca o functie care intoarce numarul atomilor dintr-o lista, de la orice nivel.

(defun nratomi(l)
    (cond
        ((atom l) 1)
        (t (apply #'+ (mapcar #'nratomi l)))
    )
)