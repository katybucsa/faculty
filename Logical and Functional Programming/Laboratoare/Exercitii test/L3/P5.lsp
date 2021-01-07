;5. Definiti o functie care testeaza apartenenta unui nod intr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1... lista_noduri__subarbn) Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul este 'b => adevarat

(defun sau(l)
    (cond
        ((null l) nil)
        ((and(atom (car l)) (car l)) t)
        ((atom (car l)) (sau (cdr l)))
        (t (or (sau (car l)) (sau (cdr l))))
    )
)

(defun apartine(l x)
    (cond
        ((and (atom l) (equal l x)) t)
        ((atom l) nil)
        (t (sau (mapcar #'(lambda(l)
                                (apartine l x)
                          )
                          l)))
    )
)