;a) Definiti o functie care intoarce produsul a doi vectori.

(defun produs(v1 v2)
    (cond
        ((and (null v1) (null v2)) 1)
        ((null v1) (* (car v2) (produs v1 (cdr v2))))
        ((null v2) (* (car v1) (produs (cdr v1) v2)))
        (t (* (car v1) (car v2) (produs (cdr v1) (cdr v2))))
    )
)

;b) Sa se construiasca o functie care intoarce adancimea unei liste.

(defun ad(l n)
    (cond 
        ((null l) n)
        ((listp (car l)) (max (ad (car l) (+ n 1)) (ad (cdr l) n)))
        (t (ad (cdr l) n))
    )
)

(defun adanc(l)
    (ad l 1)
)


;c) Definiti o functie care sorteaza fara pastrarea dublurilor o lista liniara.

(defun detMin(l)
    (cond
        ((null (cdr l)) (car l))
        ((< (car l) (detMin (cdr l))) (car l))
        (t (detMin (cdr l)))
    )
)

(defun elimin(l e)
    (cond
        ((null l) nil)
        ((= (car l) e) (elimin (cdr l) e))
        (t (cons (car l) (elimin (cdr l) e)))
    )
)

(defun sorteaza(l)
    (cond 
        ((null l) nil)
        (t (cons (detMin l)(sorteaza (elimin  l (detMin l)))))
    )
)

;d) Sa se scrie o functie care intoarce intersectia a doua multimi.

(defun membru(l e)
    (cond 
        ((null l) nil)
        ((listp (car l)) (or (membru (car l) e) (membru (cdr l) e)))
        ((equal (car l) e) t)
        (t (membru (cdr l) e))
    )
)

(defun intersect(m1 m2)
    (cond
        ((null m1) nil)
        ((membru m2 (car m1)) (cons (car m1) (intersect (cdr m1) m2)))
        (t (intersect (cdr m1) m2))
    )
)