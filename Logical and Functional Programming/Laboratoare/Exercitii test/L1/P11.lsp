;a) Sa se determine cel mai mic multiplu comun al valorilor numerice dintr-o lista neliniara.

(defun detDiv(a b)
    (cond 
        ((= b 0) a)
        (t (detDiv b (mod a b)))
    )
)

(defun cmmmc(l m)
    (cond 
        ((null l) m)
        ((and (numberp (car l)) (= m -1)) (cmmmc (cdr l) (car l)))
        ((numberp (car l)) (cmmmc (cdr l) (/ (* (car l) m) (detDiv (car l) m))))
        ((atom (car l)) (cmmmc (cdr l) m))
        (t (/ (* (cmmmc (car l) m) (cmmmc (cdr l) m)) (detDiv (cmmmc (car l) m) (cmmmc (cdr l) m))))
    )
)

(defun multiplu(l)
    (cmmmc l -1)
)

;b) Sa se scrie o functie care sa testeze daca o lista liniara formata din numere intregi are aspect de "munte"(o secvență se spune ca are aspect de "munte" daca elementele cresc pana la un moment dat, apoi descresc. De ex. 10 18 29 17 11 10).


(defun munte1(l n)
    (cond 
        ((and (null (cddr l)) (= n 1)) t)
        ((and (< (car l) (cadr l)) (>(cadr l) (caddr l))) (munte1 (cdr l) (+ n 1)))
        ((or(and (< (car l) (cadr l)) (<(cadr l) (caddr l))) (and (> (car l) (cadr l)) (>(cadr l) (caddr l)))) (munte1 (cdr l) n))
    )
)

(defun munte(l)
    (cond
        ((and (not(null (cddr l))) (< (car l) (cadr l))) (munte1 l 0))
    )
)

;c) Sa se elimine toate aparitiile elementului numeric maxim dintr-o lista neliniara.


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

;d) Sa se construiasca o functie care intoarce produsul atomilor numerici pari dintr-o lista, de la orice nivel.


(defun produs(l)
    (cond
        ((null l) 1)
        ((listp (car l)) (* (produs(car l)) (produs (cdr l))))
        ((numberp (car l)) (* (car l) (produs (cdr l))))
        (t (produs (cdr l)))
    )
)
     
