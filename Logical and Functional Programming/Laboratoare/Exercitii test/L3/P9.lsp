;9. Definiti o functie care substituie un element E prin elementele unei liste L1 la toate nivelurile unei liste date L.

(defun substit(l e lst)
    (cond
        ((equal e l) lst)
        ((atom l) l)
        (t (mapcar #'(lambda (l)
                        (substit l e lst)
                     )
                     l))
    )
)