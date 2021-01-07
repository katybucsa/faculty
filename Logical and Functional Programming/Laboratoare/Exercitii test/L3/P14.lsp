;14. Definiti o functie care da adancimea unui arbore n-ar reprezentat sub forma(radacina lista_noduri_subarb1...lista_noduri_subarbn) Ex: adancimea arborelui este (a (b (c)) (d) (e (f))) este 3

(defun ad(l)
    (cond
        ((null (cdr l)) 0)
        (t (+ 1 (apply #'max(mapcar #'ad (cdr l)))))
    )
)