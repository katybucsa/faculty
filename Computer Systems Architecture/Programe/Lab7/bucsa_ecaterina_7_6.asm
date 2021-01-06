;Bucsa Ecaterina, grupa 211/2, 21.11.2017,laboratorul 7, problema 6

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
   sir dw 12345,20778,4596,123,0abcdh
   l equ ($-sir)/2
   zece dw 10
   c times 5*l dw 0;sirul in care se retin toate cifrele numerelor din sir
   
   ;dest times  
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Dandu-se un sir de cuvinte sa se obtina sirul (de octeti) ;cifrelor in baza zece ale fiecarui cuvant din acest sir.
        ;Exemplu:
        ;daca avem sirul:
        ;sir DW 12345, 20778, 4596 
        ;obtinem rezultatul
        ;1, 2, 3, 4, 5, 2, 0, 7, 7, 8, 4, 5, 9, 6.
        
        
        mov ecx,l;parcurgem elementele sirului intr-o bucla loop cu l iteratii
        cld;parcurgem sirul de la stanga la dreapta
        jecxz final
        mov esi,0;pornim de la primul element din sirul sursa
        mov edi,0;stocam elementele in sirul c incepand cu prima pozitie
        repeta:
            mov ax,[sir+esi];in ax se stocheaza pe rand cate un element din sirul sursa
            mov dx,0
            mov bl,0;in bl vom retine numarul de cifre al fiecarui element din sirul sursa
            repeta1:
                div word[zece];se fac impartiri succesive la 10 pentru o obtine toate cifrele unui element
                push dx;stocam toate cifrele pe stiva 
                add bl,1
               cmp ax,0
            jz repeta2;daca catul este 0 inseamna ca am obtinut toate cifrele si putem parasi bucla repeta1
            
            mov dx,0
            jmp repeta1
            repeta2:
                pop dx;se scot de pe stiva toate cifrele unui element in ordinea aparitiei
                mov [c+edi],dx;se stocheaza cifrele in sirul destinatie
                inc edi
                sub bl,1
                cmp bl,0
                jz sfarsit;daca am scos toate cifrele de pe stiva putem parasi bucla repeta2
                jmp repeta2
            sfarsit:
            add esi,2
        loop repeta;daca ecx>0 se reia ciclul
        final:
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
