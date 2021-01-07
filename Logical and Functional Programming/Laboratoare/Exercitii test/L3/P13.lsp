;13. Definiti o functie care substituie un element prin altul la toate nivelurile unei liste date.

(defun substituie(l e i)
    (cond
        ((equal l e) i)
        ((atom l) l)
        (t (mapcar #'(lambda(lst)
                        (substituie lst e i)
                     )
                     l))
    )
)