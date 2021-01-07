(DEFUN  listaLiniara ( l )
	(COND
		((NULL l) NIL)
		((LISTP (CAR l)) (APPEND (listaLiniara(CAR l)) (listaLiniara(CDR l))))
		(T (CONS (CAR l) (listaLiniara(CDR l))))
	)
)