;10. Definiti o functie care determina numarul nodurilor de pe nivelul k dintr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1 ... lista_noduri_subarbn) Ex: arborelele este (a (b (c)) (d) (e (f))) si k=1 => 3 noduri

(defun nivelk(l k)
    (cond
        ((and (atom l) (= k -1)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda(l)
                                    (nivelk l (- k 1))
                                )
                                l)))
    )
)
