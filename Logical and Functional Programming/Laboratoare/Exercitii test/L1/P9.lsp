;a) Sa se scrie o functie care intoarce diferenta a doua multimi.

(defun apare(e l)
    (cond
        ((null l) nil)
        ((equal e (car l)) t)
        (t (apare e (cdr l)))
    )
)



(defun dif(m1 m2)
    (cond
        ((null m1) nil)
        ((not(apare (car m1) m2)) (cons (car m1) (dif (cdr m1) m2)))
        (t (dif (cdr m1) m2))
    )
)

;b) Definiti o functie care inverseaza o lista impreuna cu toate sublistele sale de pe orice nivel.

(defun invers(l)
    (cond
        ((null l) nil)
        ;((and (atom (car l)) (null (cdr l))) (list (car l)))
        ((atom (car l)) (append (invers (cdr l)) (list (car l))))
        (t (append (invers (cdr l)) (list(invers (car l)))))
    )
)

;c) Dandu-se o lista, sa se construiasca lista primelor elemente ale tuturor elementelor lista ce au un numar impar de elemente la nivel superficial. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10 11)) => (1 3 9).

(defun limpara(l)
    (cond
        ((null l) nil)
        ((and (listp (car l)) (oddp (length (car l)))) (cons (caar l) (append (limpara(car l)) (limpara (cdr l)))))
        (t (limpara (cdr l)))
    )
)


(defun impar(l)
    (cond 
        ((oddp (length l)) (cons (car l) (limpara l)))
        (t (limpara l))
    )
)


;d) Sa se construiasca o functie care intoarce suma atomilor numerici dintr-o lista, de la nivelul superficial.

(defun suma(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ (car l) (suma (cdr l))))
        (t (suma (cdr l)))
    )
)