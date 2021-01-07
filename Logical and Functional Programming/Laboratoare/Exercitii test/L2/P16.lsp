;16. Sa se decida daca un arbore de tipul (2) este echilibrat (diferenta dintre adancimile celor 2 subarbori nu este mai mare decat 1).

(defun echilibrat(l)
    (cond
        ((null l) (list -1 -1))
        ((and (<= (abs (- (+ (car (echilibrat (cadr l))) 1) (+ (cadr (echilibrat (cadr l))) 1))) 1)
              (<= (abs (- (+ (car (echilibrat (caddr l))) 1) (+ (cadr (echilibrat (caddr l))) 1))) 1))
            (list (max (+ (car (echilibrat (cadr l))) 1) (+ (cadr (echilibrat (cadr l))) 1)) 
            (max (+ (car (echilibrat (caddr l))) 1) (+ (cadr (echilibrat (caddr l))) 1))))
        (t nil)
    )
)


(defun echi(l)
    (cond
        ((null (echilibrat l)) nil)
        ((<= (abs (- (car (echilibrat l)) (cadr (echilibrat l)))) 1) t)
    )
)