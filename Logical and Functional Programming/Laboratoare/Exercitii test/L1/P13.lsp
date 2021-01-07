;a) Sa se intercaleze un element pe pozitia a n-a a unei liste liniare.

(defun adauga(l e n)
    (cond 
        ((null l) nil)
        ((= n 1) (cons e l))
        (t (cons (car l) (adauga (cdr l) e (- n 1))))
    )
)

;b) Sa se construiasca o functie care intoarce suma atomilor numerici dintr-o lista, de la orice nivel.

(defun suma(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ (car l) (suma (cdr l))))
        ((atom (car l)) (suma (cdr l)))
        (t (+ (suma (car l)) (suma (cdr l))))
    )
)


;c) Sa se scrie o functie care intoarce multimea tuturor sublistelor unei liste date. Ex: Ptr. lista ((1 2 3) ((4 5) 6)) => ((1 2 3) (4 5) ((4 5) 6)).

(defun subl(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (list (car l)) (subl (car l)) (subl (cdr l))))
        (t (subl (cdr l)))
    )
)

;d) Sa se scrie o functie care testeaza egalitatea a doua multimi, fara sa se faca apel la diferenta a doua multimi.

(defun membru(l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) t)
        (t (membru (cdr l) e))
    )
)

(defun inclus(m1 m2)
    (cond
        ((null m1) t)
        ((membru m2 (car m1)) (inclus (cdr m1) m2))
    )
)

(defun egal(m1 m2)
    (and(inclus m1 m2) (inclus m2 m1))
)

