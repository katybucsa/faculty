(defun dublu(l N P)
    (cond
        ((null l) nil)
        ((=(mod P N) 0) (append (list (car l) (car l)) (dublu (cdr l) N (+ P 1))))
        (t (cons (car l) (dublu (cdr l) N (+ P 1))))
    )
)

(defun dubleaza(l N)
    (dublu l N 1)
)

(defun nrAtomi(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ 1 (nrAtomi (cdr l))))
        (t (nrAtomi (cdr l)))
    )
)


(defun verif(l)
    (cond
        ((null l) t)
        ((atom (car l)) (verif (cdr l)))
        (t (and (oddp (nrAtomi (car l))) (verif (cdr l))))
    )
)

(defun nrsubAtomi(l)
    (cond
        ((atom l) nil)
        ((verif (list l)) (apply #'append (list l) (mapcar #'nrsubAtomi l)))
        (t (apply #'append (mapcar #'nrsubAtomi l)))
    )
)