;Definiti o functie care obtine dintr-o lista data lista tuturor atomilor nenumerici
;care apar, pe orice nivel, dar in ordine inversa.

(defun atomi(l)
    (cond
        ((null l) nil)
        ((numberp (car l)) (atomi (cdr l)))
        ((atom (car l)) (append (atomi (cdr l))(list (car l))))
        (t (append (atomi (cdr l)) (atomi (car l))))
    )
)
;(((A B)2 C)3(D 1 E))==>(E D C B A)