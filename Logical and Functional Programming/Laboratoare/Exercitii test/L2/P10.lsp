;10. Se da un arbore de tipul (2). Sa se precizeze nivelul pe care apare un nod x in arbore. Nivelul radacii se considera a fi 0.

(defun niv(l nod n)
    (cond
        ((null l) nil)
        ((equal nod (car l)) n)
        ((null (niv (cadr l) nod (+ n 1))) (niv (caddr l) nod (+ n 1)))
        (t (niv (cadr l) nod (+ n 1)))
    )
)

(defun nivel( l nod)
    (niv l nod 0)
)