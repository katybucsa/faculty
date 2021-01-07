;a) Sa se scrie o functie care testeaza daca o lista este liniara.

(defun liniara(l)
    (cond 
        ((null l) t)
        ((listp (car l)) nil)
        (t (liniara (cdr l)))
    )
)

;b) Definiti o functie care substituie prima aparitie a unui element intr-o lista data.


(defun apare(e l)
    (cond
        ((null l) nil)
        ((equal e (car l)) t)
        (t (apare e (cdr l)))
    )
)

(defun subs(l e1 e2 i)
    (cond 
        ((null l) nil)
        ((and (equal (car l) e1) (= i 0)) (cons e2 (cdr l)))
        ((and (listp (car l)) (apare e1 (car l)))(cons (subs (car l) e1 e2 1) (cdr l)))
        (t (cons (car l) (subs (cdr l) e1 e2 0)))
    )
)

(defun substit(l e1 e2)
    (subs l e1 e2 0)
)

;c) Sa se inlocuiasca fiecare sublista a unei liste cu ultimul ei element. Prin sublista se intelege element de pe primul nivel, care este lista. Exemplu: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c f)
;(a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)

(defun detUlt(l e)
    (cond
        ((null l) e)
        ((null (car l)) (detUlt (cdr l) e))
        ((atom (car l)) (detUlt (cdr l) (car l)))
        ((null (cdr l)) (detUlt (car l) e))
        (t (detUlt (cdr l) e))
    )
)   

(defun det(l e)
    (cond
        ((null l) e)
        ((null (car l)) (det (cdr l) e))
        ((atom (car l)) (det (cdr l) (car l)))
        (t (det (cdr l) e))
    )
)

(defun ultim(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (cons (detUlt (car l) nil) (ultim (cdr l))))
        (t (ultim (cdr l)))
    )
)

(defun inloc(l)
    (cons (det l nil) (ultim l))
)
   

;d) Definiti o functie care interclaseaza fara pastrarea dublurilor doua liste liniare sortate.


(defun inter(l1 l2)
    (cond 
        ((null l1) l2)
        ((null l2) l1)
        ((< (car l1) (car l2)) (cons (car l1) (inter (cdr l1) l2)))
        ((> (car l1) (car l2)) (cons (car l2) (inter l1 (cdr l2))))
        (t (cons (car l2) (inter (car l1) (cdr l2))))
    )
)   
        
        
        
