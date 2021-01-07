;a) Sa se scrie o functie care intoarce reuniunea a doua multimi.

(defun membru(l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) t)
        (t (membru (cdr l) e))
    )
)

(defun adauga(l c)
    (cond 
        ((null l) c)
        ((membru c (car l)) (adauga (cdr l) c))
        (t (adauga (cdr l) (cons (car l) c)))
    )
)

(defun reuniune(m1 m2)
    (adauga m1 m2)
)

;b) Sa se construiasca o functie care intoarce produsul atomilor numerici dintr-o lista, de la orice nivel.

(defun produs(l)
    (cond
        ((null l) 1)
        ((numberp (car l)) (* (car l) (produs (cdr l))))
        ((atom (car l)) (produs (cdr l)))
        (t (* (produs (car l)) (produs (cdr l))))
    )
)


;c) Definiti o functie care sorteaza cu pastrarea dublurilor o lista liniara.


(defun inter(l1 l2)
    (cond 
        ((null l1) l2)
        ((null l2) l1)
        ((< (car l1) (car l2)) (cons (car l1) (inter (cdr l1) l2)))
        (t (cons (car l2) (inter l1 (cdr l2))))
    )
)


; d) Definiti o functie care construiește o listă cu pozițiile elementului minim dintr-o listă liniară numerică.


(defun detMin(l)
    (cond
        ((null (cdr l)) (car l))
        ((< (car l) (detMin (cdr l))) (car l))
        (t (detMin (cdr l)))
    )
)

(defun poz(l m p)
    (cond
        ((null l) nil)
        ((= (car l) m) (cons p (poz (cdr l) m (+ p 1))))
        (t (poz (cdr l) m (+ p 1)))
    )
)


(defun pozitii(l)
    (poz l (detMin l) 1)
)