;a) Dandu-se o lista liniara, se cere sa se elimine elementele din N in N.

(defun elim(l n p)
    (cond
        ((null l) nil)
        ((= (mod p n) 0) (elim (cdr l) n (+ p 1)))
        (t (cons (car l) (elim (cdr l) n (+ p 1))))
    )
)

(defun elimina(l n)
    (elim l n 1)
)
        
;b) Sa se scrie o functie care sa testeze daca o lista liniara formata din numere intregi are aspect de "vale"(o secvență se spune ca are aspect de "vale" daca elementele descresc pana la un moment dat, apoi cresc. De ex. 10 8 6 17 19 20).


(defun vale1(l n)
    (cond 
        ((and (null (cddr l)) (= n 1)) t)
        ((and (> (car l) (cadr l)) (<(cadr l) (caddr l))) (vale1 (cdr l) (+ n 1)))
        ((or(and (< (car l) (cadr l)) (<(cadr l) (caddr l))) (and (> (car l) (cadr l)) (>(cadr l) (caddr l)))) (vale1 (cdr l) n))
    )
)

(defun vale(l)
    (cond
        ((and (not(null (cddr l))) (> (car l) (cadr l))) (vale1 l 0))
    )
)

;c) Sa se construiasca o functie care intoarce minimul atomilor numerici dintr-o lista, de la orice nivel.

(defun detMin(l m)
    (cond
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (< (car l) m))) (detMin (cdr l) (car l)))
        ((or(numberp (car l))(atom (car l))) (detMin (cdr l) m))
        (t (detMin (cdr l) (detMin (car l) m)))
    )
)


(defun minim(l)
    (detMin l nil)
)


;d) Sa se scrie o functie care sterge dintr-o lista liniara toate aparitiile elementului maxim numeric.------>11 c)


(defun detMax(l m)
    (cond
        ((null l) m)
        ((and (numberp (car l)) (or (null m) (> (car l) m))) (detMax (cdr l) (car l)))
        ((or(numberp (car l))(atom (car l))) (detMax (cdr l) m))
        (t (detMax (cdr l) (detMax (car l) m)))
    )
)

(defun elim(l m)
    (cond
        ((null l) nil)
        ((listp (car l))(cons (elim (car l) m) (elim (cdr l) m)))
        ((equal (car l) m) (elim (cdr l) m))
        (t (cons (car l) (elim (cdr l) m)))
    )
)

(defun elimina(l)
    (elim l (detMax l nil))
)