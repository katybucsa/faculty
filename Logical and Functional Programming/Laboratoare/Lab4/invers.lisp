(DEFUN invers1(l c)
	(COND
		((NULL l) c)
		((NOT(LISTP (CAR l))) (invers1 (CDR l) (CONS (CAR l) c)))
		((NULL c) (CONS (invers1 (CAR l) c) (invers1 (CDR l) c))) 
		(T (APPEND c (invers1 l NIL)))
	)
) 

(DEFUN invers(l)
	(invers1 l NIL)
)