;a) Definiti o functie care interclaseaza cu pastrarea dublurilor doua liste liniare sortate.

(defun inter(l1 l2)
    (cond 
        ((null l1) l2)
        ((null l2) l1)
        ((< (car l1) (car l2)) (cons (car l1) (inter (cdr l1) l2)))
        (t (cons (car l2) (inter l1 (cdr l2))))
    )
)


;b) Definiti o functie care substituie un element E prin elementele unei liste L1 la toate nivelurile unei liste date L.

(defun inloc(l e i)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (list (inloc (car l) e i)) (inloc (cdr l) e i))) 
        ((equal (car l) e) (append i (inloc (cdr l) e i)))
        (t (cons (car l) (inloc (cdr l) e i)))
    )
)

;c) Definiti o functie care determina suma a doua numere in reprezentare de lista si calculeaza numarul zecimal corespunzator sumei.

(defun invers(l)
    (cond 
        ((null l) nil)
        (t (append (invers (cdr l)) (list (car l))))
    )
)


(defun suma(l1 l2 p i)
    (cond 
        ((and (null l1) (null l2) (= i 0)) 0)
        ((and (null l1) (null l2) (/= i 0)) (* i (expt 10 p)))
        ((null l1) (+ (* (expt 10 p) (mod (+ (car l2) i) 10)) (suma l1 (cdr l2) (+ p 1) (floor(/ (+ (car l2) i) 10)))))
        ((null l2) (suma l2 l1 p i))
        (t (+ (* (mod (+ (car l1) (car l2) i) 10) (expt 10 p)) (suma (cdr l1) (cdr l2) (+ p 1) (floor(/ (+ (car l1) (car l2) i) 10)))))
    )
)       

    
(defun sum(l1 l2)
    (suma (invers l1)(invers l2) 0 0)
)


;d) Definiti o functie care intoarce cel mai mare divizor comun al numerelor dintr-o lista liniara.

(defun detDiv(a b)
    (cond 
        ((= b 0) a)
        (t (detDiv b (mod a b)))
    )
)


(defun div(l d)
    (cond 
        ((null l) d)
        ((and (numberp (car l)) (= d -1)) (div (cdr l) (car l)))
        ((numberp (car l)) (div (cdr l) (detDiv (car l) d)))
        (t (div (cdr l) d))
    )
)


(defun divizor(l)
    (div l -1)
)
