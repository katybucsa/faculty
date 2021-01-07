;a) Definiti o functie care intoarce produsul scalar a doi vectori.

(defun scalar( v1 v2)
    (+(* (car v1) (car v2)) (* (cadr v1) (cadr v2)))
)

;b) Sa se construiasca o functie care intoarce maximul atomilor numerici dintr-o lista, de la orice nivel.


(defun detMax(l m)
    (cond
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (> (car l) m))) (detMax (cdr l) (car l)))
        ((or(numberp (car l))(atom (car l))) (detMax (cdr l) m))
        (t (detMax (cdr l) (detMax (car l) m)))
    )
)

(defun maxim(l)
    (detMax l nil)
)

;c) Sa se scrie o functie care intoarce lista permutarilor unei liste date.


(defun insPoz(e l poz)
    (cond
        ((= poz 1) (cons e l))
        (t (cons (car l) (insPoz e (cdr l) (- poz 1))))
    )
)

(defun insToate(e l k)
    (cond 
        ((= k 0) nil)
        (t (cons (insPoz e (car l) k)(insToate e (car l) (- k 1))) (cons (insPoz e (cdr l) k)(insToate e (cdr l) (- k 1)))))
    )
)

(defun ins(l c)
    (cond
        ((null l) c)
        (t (ins (cdr l) (insToate (car l) c (+ (length (car c)) 1))))
    )
)


(defun elim(e l)
    (cond
        ((null l) nil)
        ((equal (car l) e) (cdr l))
        (t (cons (car l) (elim e (cdr l))))
    )
)




(defun perm(l)
    (cond
        ((null l) nil)
        (t (ins (cdr l) (list (list(car l)))))
    )
)

(defun membru(l e)
    (cond 
        ((null l) nil)
        ((equal (car l) e) t)
        (t (membru (cdr l) e))
    )
)
   
(defun mult(l c)
    (cond
        ((null l) c)
        ((membru c (car l)) (mult (cdr l) c))
        (t (mult (cdr l) (cons (car l) c)))
    )
)   
        

        
(defun permutari(l)
   (perm l l)
)



;d) Sa se scrie o functie care intoarce T daca o lista are numar par de elemente pe primul nivel si NIL in caz contrar, fara sa se numere elementele listei.


(defun lung(l i)
    (cond
        ((and (null l) (evenp i)) t)
        ((null l) nil)
        ((= i 0) (lung (cdr l) 1))
        (t  (lung (cdr l) 0))
    )
)

(defun lpi(l)
    (lung l 0)
)