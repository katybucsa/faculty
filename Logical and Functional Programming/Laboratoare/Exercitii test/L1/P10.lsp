;a) Sa se construiasca o functie care intoarce produsul atomilor numerici dintr-o lista, de la nivelul superficial.

(defun produs(l)
     (cond
        ((null l) 1)
        ((numberp (car l)) (* (car l) (produs (cdr l))))
        (t (produs (cdr l)))
    )
)

;b) Sa se scrie o functie care, primind o lista, intoarce multimea tuturor perechilor din lista. De exemplu: (a b c d) --> ((a b) (a c) (a d)(b c) (b d) (c d))

(defun per(e l)
    (cond  
        ((null l) nil)
        (t (cons (list e (car l)) (per e (cdr l))))
    )
)

(defun perechi(l)
    (cond 
        ((null(cdr l)) nil)
        (t (append (per(car l) (cdr l)) (perechi (cdr l))))
    )
)


;c) Sa se determine rezultatul unei expresii aritmetice memorate in preordine pe o stiva. Exemple:
;(+ 1 3) ==> 4 (1 + 3)
;(+ * 2 4 3) ==> 11 ((2 * 4) + 3)
;(+ * 2 4 - 5 * 2 2) ==> 9 ((2 * 4) + (5 - (2 * 2))

(defun invers(l)
    (cond 
        ((null l) nil)
        (t (append (invers (cdr l)) (list (car l))))
    )
)


(defun rezultat(l op)
    (cond 
        ((null l) (car op))
        ((numberp (car l)) (rezultat (cdr l) (cons (car l) op)))
        (t (rezultat (cdr l) (cons (funcall (car l) (car op)(cadr op)) (cddr op))))
    )
)
        
        
(defun rez(l)
    (rezultat (invers l) nil)
)


;d) Definiti o functie care, dintr-o lista de atomi, produce o lista de perechi (atom n), unde atom apare in lista initiala de n ori. De ex:
;(A B A B A C A) --> ((A 4) (B 2) (C 1)).


(defun nrAp(e l)
    (cond 
        ((null l) 0)
        ((equal (car l) e) (+ (nrAp e (cdr l)) 1))
        (t (nrAp e (cdr l)))
    )
)

(defun elimin(l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) (elimin (cdr l) e))
        (t (cons (car l) (elimin (cdr l) e)))
    )
)


(defun perechiN(l)
    (cond 
        ((null l) nil)
        (t (cons (list (car l) (nrAp (car l) l)) (perechiN (elimin l (car l)))))
    )
)