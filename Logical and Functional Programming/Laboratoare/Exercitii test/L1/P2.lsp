;a) Definiti o functie care selecteaza al n-lea element al unei liste, sau NIL, daca nu exista.

(defun select(l n)
    (cond 
        ((null l) nil)
        ((= n 1) (car l))
        (t (select (cdr l) (- n 1)))
    )
)

;b) Sa se construiasca o functie care verifica daca un atom e membru al unei liste nu neaparat liniara.

(defun membru(l e)
    (cond 
        ((null l) nil)
        ((listp (car l)) (or (membru (car l) e) (membru (cdr l) e)))
        ((equal (car l) e) t)
        (t (membru (cdr l) e))
    )
)

;c) Sa se construiasca lista tuturor sublistelor unei liste. Prin sublista se intelege fie lista insasi, fie un element de pe orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
;( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (4 5) (6 7) (9 10) ).

(defun subl(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (list (car l)) (subl (car l)) (subl (cdr l))))
        (t (subl (cdr l)))
    )
)
        
(defun sublista(l)
    (append (list l) (subl l))
)


;d) Sa se scrie o functie care transforma o lista liniara intr-o multime.
(defun mult(l c)
    (cond
        ((null l) c)
        ((membru c (car l)) (mult (cdr l) c))
        (t (mult (cdr l) (cons (car l) c)))
    )
)


(defun multime(l)
    (mult l nil)
)