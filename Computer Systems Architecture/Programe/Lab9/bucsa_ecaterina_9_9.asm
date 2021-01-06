;Bucsa Ecaterina, grupa 211/2, 10.12.2017,laboratorul 9, problema 9

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,fopen,fclose,fread,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll  
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll
import printf msvcrt.dll  ; exit is a function that ends the calling process. It is         defined in msvcrt.dll
                                    ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    nume_fis db 'citire.txt',0; numele fisierului ce va fi deschis 
    mod_acces db 'r',0; modul de deschidere a fisierului, r- pentru citire 
    descriptor_fis dd -1
    nr_car_citite dd 0; variabila in care se salveaza numarul de caractere citite in etapa curenta
    len equ 1; numarul de caractere citite din fisier intr-o etapa 
    a resb len; in variabila a se va citi fiecare caracter din fisier
    format db 'Caracterul %c apare de %d ori',0; formatul de afisare pe ecran
    ap dd 0; la finalul programului in ap se va memora numarul maxim de aparitii ale unui caracter special din fisier
    el dd 0; in  el se va afla la finalul programului caracterul special cu numarul maxim de aparitii
    len_c dd 0; lungimea sirului de caractere speciale format din caracterele textului din fisier
    c  db 0; in c se va memora sirul cu caracterele speciale din fisier
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un fisier text. Sa se citeasca continutul fisierului, sa se determine caracterul special (diferit de litera) cu ;cea mai mare frecventa si sa se afiseze acel caracter, impreuna cu frecventa acestuia. 
        ;Numele fisierului text este definit in segmentul de date.
        
        push dword mod_acces
        push dword nume_fis
        call [fopen]; se apeleaza functia fopen pentru a deschide fisierul; functia va returna in EAX descriptorul fisierului sau 0 in caz de eroare
        add esp, 4*2; se elibereaza parametrii de pe stiva 
        
        mov [descriptor_fis],eax; se salveaza valoarea returnata de fopen in descriptor_fis
        
        cmp eax,0; verificam daca functia fopen a deschis cu succes fisierul(Eax!=0)
        je final
        
        
        mov edi,0
        bucla:
            ; se citeste un caracter di fisier
            push dword [descriptor_fis]
            push dword len
            push dword 1
            push dword a
            call [fread]
            add esp, 4*4
           
            ;eax=numarul de caractere citite 
            cmp eax, 0; daca numarul de caractere citite este 0, am terminat de parcurs fisierul
            je cleanup
            
            mov [nr_car_citite],eax; salvam numarul de caractere citite
            
            mov bl,[a]; memoram in bl caracterul citit din fisier 
            
            ; verificam daca in bl este un caracter special(diferit de litera)
            
            ;cmp bl,'0'
            ;jl adauga
         
            ;cmp bl,'z'
            ;jg adauga
                
            ;cmp bl,'A'
            ;jl e1
           
            ;cmp bl,'Z'
            ;jg e2
            ;jmp sfarsit
            
            ;e1:
            ;    cmp bl,'9'
            ;    jg adauga
            ;    jmp sfarsit
                
            ;e2:
            ;    cmp bl,'a'
            ;    jl adauga
            ;    jmp sfarsit
                
                
            
            cmp bl,'A'
            jl adauga
            jmp et
            
            et:
            cmp bl,'z'
            jg adauga
            jmp et1
            
            et1:
            cmp bl,'a'
            jl et2
            jmp sfarsit
            
            et2:
               cmp bl,'Z'
               jg adauga
               jmp sfarsit
               
            ;se adauga caracterul special in sirul c
            adauga:
                mov [c+edi],bl
                inc edi
                jmp sfarsit
                
            sfarsit:  
            jmp bucla; reluam bucla pentru a citi urmatorul caracter din fisier  
            
        cleanup:
            push dword [descriptor_fis]
            call [fclose]; se apeleaza functia fclose pentru a inchide fisierul
            add esp, 4; eliberam parametrii de pe stiva 
            mov [len_c],edi
            
        final:
            mov ebx,0; in bl se va stoca numarul maxim de aparitii ale unui caracter in sirul c
            mov esi,c
            cld; se parcurge de la stanga la dreapta sirul c 
            mov ecx,[len_c]
            jecxz finalp; parcurgem sirul c in bucla repeta cu len_c iteratii
            repeta:
                lodsb; in al se incarca octetul curent din c 
                mov edx,0; in dl memoram numarul de aparitii ale caracterului din al in sirul c 
                mov edi,[len_c]
                dec edi
                ; parcurgem pentru fiecare element din sirul c, intreg sirul c insa de la dreapta la stanga
                repeta1:
                    cmp al,[c+edi]
                    je et3; 
                    jmp sfarsit1
                
                et3:
                    add edx,1
                    cmp edx,ebx; daca caracterul curent are un  numar de aparitii mai mare decat numarul maxim salvam caracterul si numarul sau de aparitii prin jump la et4
                    jg et4
                    jmp sfarsit1
            
                et4:
                    mov ebx,edx
                    mov [el],al
                    jmp sfarsit1
            
                sfarsit1:
                    dec edi
                    cmp edi,-1
                    je finalp1
                    
                    jmp repeta1
            
                finalp1:
                    loop repeta; daca mai sunt elemente de parcurs(ecx>0) se reia ciclul
                finalp:
                    ;afisam caracterul special cu numarul maxim de aparitii
                    mov [ap],ebx
                    push dword [ap]; se pune pe stiva valoarea lui ap 
                    push dword [el]; se pune pe stiva valoarea lui el 
                    push dword format; se pune pe stiva adresa string-ului format
                    call [printf]; se apeleaza functia printf pentru afisare
                    add esp,4*3; se elibereaza parametrii de pe stiva
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
