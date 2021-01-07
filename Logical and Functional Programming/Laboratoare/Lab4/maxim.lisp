(DEFUN maxim1(l)
	(COND 
		((NULL l) -999999)
		((AND (NUMBERP (CAR l)) (> (CAR l) (maxim1 (CDR l)))) (CAR l))
		(T (maxim1 (CDR l)))
	)
)


(DEFUN maxim(l)
	(COND
		((NULL l) '(Lista este vida!))
		((= (maxim1 l) -999999) '(Nu exista element numeric la nivel superficial))
		(T  (maxim1 l))
	)
) 