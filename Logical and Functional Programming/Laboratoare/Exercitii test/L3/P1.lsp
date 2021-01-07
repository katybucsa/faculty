;1. Sa se construiasca o functie care intoarce adancimea unei liste.

(defun adanc(l)
    (cond 
        ((listp  l) (max (apply #'+ 1 (mapcar #'(lambda(lst)
                                                    (adanc lst)
                                        )
                                        l))))
        (t 0)
    )
)