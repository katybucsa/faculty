bits 32                  
segment code use32 public code
global intercalare

intercalare: 
    mov ecx,[esp+4]
    .repeta:
        lodsb; in al se incarca pe rand cate un octet al sirului din esi 
        mov [edi],al; introducem in sirul destinatie elementul curent din esi
        inc edi
        cmp edx,1
        je .nu
        mov [edi],al; daca valoarea lui edx=1(in esi se afla primul sir care se adauga in sirul destinatie) atunci caracterul din esi se adauga de doua ori in sirul destinatie, altfel se trece la elementul urmator
        .nu:
        inc edi
        loop .repeta
        ret 4; numarul de octeti ce trebuie eliberati de pe stiva;