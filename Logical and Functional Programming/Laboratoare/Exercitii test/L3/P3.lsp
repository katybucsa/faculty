;3. Sa se construiasca o functie care verifica daca un atom e membru al unei liste nu neaparat liniara.

(defun sau(l)
    (cond
        ((null l) nil)
        ((and(atom (car l))(equal (car l) 't)) t)
        ((atom (car l)) (sau (cdr l)))
        (t (or (sau (car l)) (sau (cdr l))))
    )
)


(defun membru(l x)
    (cond 
        ((and (atom l) (equal l x)) t)
        ((atom l) nil)
        (t (sau (mapcar #'(lambda(lst)
                    (membru lst x)
                    )
                    l)))
    )
)