;Sa se scrie o functie care plecand de la o lista data ca argument, inverseaza numai 
;secventele continue de atomi. 
;(A B C(D(E F) G H I))==>(C B A(D (F E) I H G))
(defun invers(l c)
    (cond 
        ((null l) c)
        ((atom (car l)) (invers (cdr l) (cons (car l) c)))
        ((null c) (cons (invers (car l) c) (invers (cdr l) c)))
        (t (append  c (list (invers (car l) nil)) (invers (cdr l) nil)))
    )
)