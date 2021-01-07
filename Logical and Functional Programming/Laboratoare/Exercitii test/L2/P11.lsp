;11. Se da un arbore de tipul (2). Sa se afiseze nivelul (si lista corespunzatoare a nodurilor) avand numar maxim de noduri. Nivelul rad. se considera 0.


(defun maxnoduri(l n ln niv)
    (cond
        ((null l) (list n ln))
        ((> (+(-(length (cadr l)) 1) (-(length (caddr l)) 1)) (length (ln))) (maxnoduri (caddr l) niv 