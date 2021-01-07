;11. Sa se scrie o functie care sterge toate aparitiile unui atom de la toate nivelurile unei liste.

(defun sterge(l e)
    (cond
        ((and (atom l) (equal l e)) nil) 
        ((atom l) (list l))
        (t (list (apply #'append (mapcar #'(lambda(l)
                            (sterge l e)
                      )
                      l))))
    )
)