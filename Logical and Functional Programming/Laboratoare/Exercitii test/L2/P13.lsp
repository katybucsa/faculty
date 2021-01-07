;13. Se da un arbore de tipul (2). Sa se afiseze calea de la radacina pana la un nod x dat.


(defun apare(e l)
    (cond
        ((null l) nil)
        ((equal e (car l)) t)
        (t (or(apare e (cadr l)) (apare e (caddr l))))
    )
)

(defun cale(l x)
    (cond   
        ((equal (car l) x) (list x))
        ((apare x (cadr l)) (cons (car l) (cale (cadr l)x)))
        ((apare x (caddr l)) (cons (car l) (cale (caddr l) x)))
    )
)