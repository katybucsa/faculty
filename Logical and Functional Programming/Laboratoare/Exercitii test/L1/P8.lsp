;a) Sa se elimine elementul de pe pozitia a n-a a unei liste liniare.

(defun elimin(l n)
    (cond 
        ((null l) nil)
        ((= n 1) (elimin (cdr l) (- n 1)))
        (t (cons (car l) (elimin (cdr l) (- n 1))))
    )
)

;b) Definiti o functie care determina succesorul unui numar reprezentat cifra cu cifra intr-o lista. De ex: (1 9 3 5 9 9) --> (1 9 3 6 0 0)

(defun invers(l)
    (cond 
        ((null l) nil)
        (t (append (invers (cdr l)) (list (car l))))
    )
)


(defun succ(l i)
    (cond
        ((and (null l) (= i 0)) nil)
        ((null l) (list i))
        (t (cons (mod (+ (car l) i) 10) (succ (cdr l) (floor (/(+ (car l) i) 10)))))
    )
)

(defun succesor(l)
    (invers(succ (invers l) 1))
)


;c) Sa se construiasca multimea atomilor unei liste.Exemplu: (1 (2 (1 3 (2 4) 3) 1) (1 4)) ==> (1 2 3 4)


(defun apare(e l)
    (cond
        ((null l) nil)
        ((equal e (car l)) t)
        (t (apare e (cdr l)))
    )
)



(defun multime(l c)
    (cond 
        ((null l) c)
        ((listp (car l)) (multime (cdr l) (multime (car l) c)))
        ((apare (car l) c) (multime (cdr l) c))
        (t (multime (cdr l) (append c (list  (car l)))))
    )
)


;d) Sa se scrie o functie care testeaza daca o lista liniara este o multime.

(defun nrAp(e l)
    (cond 
        ((null l) 0)
        ((equal (car l) e) (+ (nrAp e (cdr l)) 1))
        (t (nrAp e (cdr l)))
    )
)


(defun mult( l c)
    (cond
        ((null l) t)
        ((= (nrAp (car l) c) 1) (mult (cdr l) c))
        (t nil)
    )
)

(defun eMultime(l)
    (mult l l)
)