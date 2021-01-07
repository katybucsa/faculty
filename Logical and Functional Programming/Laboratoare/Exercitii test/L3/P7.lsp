;7. Sa se scrie o functie care calculeaza suma numerelor pare minus suma numerelor impare la toate nivelurile unei liste.

(defun sum(l)
    (cond
        ((and (numberp l) (evenp l)) l)
        ((numberp l)(* l -1))
        ((atom l) 0)
        (t (apply #'+ (mapcar #'sum l)))
    )
)