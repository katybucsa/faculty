;2. Definiti o functie care obtine dintr-o lista data lista tuturor atomilor care apar, pe orice nivel, dar in aceeasi ordine. De exemplu (((A B) C) (D E)) --> (A B C D E)

(defun atomi(l)
    (cond 
        ((listp l) (mapcan #'atomi l))
        (t (list l))
    )
)