;a) Definiti o functie care intoarce suma a doi vectori.

(defun suma(v1 v2)
	(cond
		((and (null v1) (null v2)) 0)
		((null v1) (+ (car v2) (suma v1 (cdr v2))))
		((null v2) (+ (car v1) (suma (cdr v1) v2)))
		(t (+ (car v1) (car v2) (suma (cdr v1) (cdr v2))))
	)
) 

;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor care apar, pe orice nivel, dar in aceeasi ordine. De exemplu:
;(((A B) C) (D E)) --> (A B C D E)

(defun  listaliniara ( l )
	(cond
		((null l) nil)
		((listp (car l)) (append (listaliniara(car l)) (listaliniara(cdr l))))
		(t (cons (car l) (listaliniara(cdr l))))
	)
)

;c) Sa se scrie o functie care plecand de la o lista data ca argument, inverseaza numai secventele continue de atomi. Exemplu:
;(a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))

(defun invers1(l c)
	(cond
		((null l) c)
		((not(listp (car l))) (invers1 (cdr l) (cons (car l) c)))
		((null c) (cons (invers1 (car l) c) (invers1 (cdr l) c))) 
		(t (append c (invers1 l nil)))
	)
) 

(defun invers(l)
	(invers1 l nil)
)

;d) Sa se construiasca o functie care intoarce maximul atomilor numerici dintr-o lista, de la nivelul superficial.


(defun maxim1(l)
	(cond 
		((null l) -999999)
		((and (numberp (car l)) (> (car l) (maxim1 (cdr l)))) (car l))
		(t (maxim1 (cdr l)))
	)
)


(defun maxim(l)
	(cond
		((null l) '(lista este vida!))
		((= (maxim1 l) -999999) '(nu exista element numeric la nivel superficial))
		(t  (maxim1 l))
	)
) 