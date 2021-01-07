(DEFUN suma(v1 v2)
	(COND
		((AND (NULL v1) (NULL v2)) 0)
		((NULL v1) (+ (CAR v2) (suma v1 (CDR v2))))
		((NULL v2) (+ (CAR v1) (suma (CDR v1) v2)))
		(T (+ (CAR v1) (CAR v2) (suma (CDR v1) (CDR v2))))
	)
) 