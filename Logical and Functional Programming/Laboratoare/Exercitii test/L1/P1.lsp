;a) Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea, al 6-lea,....element.
(defun ins(l e p)
    (cond
        ((and (null l) (oddp p)) nil)
        ((and (null l) (evenp p)) (list e))
        ((evenp p) (append (list (car l) e) (ins (cdr l) e (+ p 1))))
        (t (cons (car l) (ins (cdr l) e (+ p 1))))
    )
)
        
(defun insert(l e)
    (ins l e 1)
)

;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor care apar, pe orice nivel, dar in ordine inversa. De exemplu: (((A B) C) (D E)) --> (E D C B A)
(defun lin(l)
    (cond 
        ((null l) nil)
        ((listp (car l)) (append (lin (cdr l)) (lin (car l))))
        (t (append (lin (cdr l)) (list (car l))))
    )
)

;c) Definiti o functie care intoarce cel mai mare divizor comun al numerelor dintr-o lista neliniara.
(defun det(a b)
    (cond 
        ((= b 0) a)
        (t (det b (mod a b)))
    )
)       


(defun diviz(l d)
    (cond
        ((null l) d)
        ((and (numberp (car l)) (= d -1)) (diviz (cdr l) (car l)))
        ((numberp (car l)) (diviz (cdr l) (det d (car l))))
        ((atom (car l)) (diviz (cdr l) d))
        ((and (/= (diviz (car l) d) -1) (/= (diviz (cdr l) d) -1) 
                (det (diviz (car l) d) (diviz (cdr l) d) )))
        ((= (diviz (car l) d) -1) (diviz (cdr l) d))
        (t (diviz (car l) d))
    )
)


(defun divizor(l)
    (cond
        ((null l) 1)
        (t (diviz l -1))
    )
)

;d) Sa se scrie o functie care determina numarul de aparitii ale unui atom dat intr-o lista neliniara.
(defun aparitii(l e n)
    (cond 
        ((null l) n)
        ((listp (car l)) (+ (aparitii (car l) e 0) (aparitii (cdr l) e 0) n))
        ((equal (car l) e) (aparitii (cdr l) e (+ n 1)))
        (t (aparitii (cdr l) e n))
    )
)

(defun apar(l e)
    (aparitii l e 0)
)