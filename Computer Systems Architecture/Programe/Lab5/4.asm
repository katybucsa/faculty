bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll 
import scanf msvcrt.dll  
                           ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a dw 0
    b dw 0
    m1 db 'a=',0
    m2 db 'b=',0
    format db '%d',0
    rezultat dd 0
    ori db '*',0
    egal db '=',0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se dau doua numere naturale a si b (a, b: word, definite in segmentul de date). Sa se calculeze produsul lor si sa se ;afiseze in urmatorul format:
        ;"<a> * <b> = <result>"
        ;Exemplu: "2 * 4 = 8"
        ;Valorile vor fi afisate in format decimal (baza 10) cu semn.
        
        push dword m1; pe stiva se pune adresa string-ului, nu valoarea
        call [printf];se apeleaza functia printf pentru afisare
        add esp, 4*1; se elibereaza parametrii de pe stiva; 4=dimensiunea unui dword; 1= nr de parametrii 
        push dword a; pe stiva se pune adresa lui a 
        push dword format; 
        call [scanf]; se apeleazafunctia scanf pentru citire
        add esp, 4*2; se elibereaza parametrii de pe stiva; 4=dimensiunea unui dword; 2=nr de parametrii
        
        push dword m2; pe stiva se pune adresa string-ului m2, nu valoarea
        call [printf]; se apeleaza functia printf pentru afisare 
        add esp, 4*1; se elibereaza parametrii de pe stiva 
        push dword b; pe stive se pune adresa lui b, nu valoarea sa 
        push dword format
        call [scanf]; se apeleaza  functia scanf pentru citire
        add esp, 4*2; se elibereaza parametriide pe stiva
       
                
        mov ax,[a]; punem in ax valoarea lui a
        mul word [b]; eax=ax*[b]=a*b 
        push dx
        push ax
        pop eax
        mov [rezultat],eax; punem in variabila rezultat produsul numerelor
        
        mov ax,[a]
        cwd   
        
        push eax; pe stiva se pune valoarea variabilei a
        push dword format
        call [printf]; se apeleaza functia printf pentru a afisa mesaj
        add esp, 4*2; se elibereaza parametrii de pe salva; 4=dimensiunea unui dword 2= numarul de parametrii
        push dword ori; pe stiva sepune adresa string-ului, nu valoarea sa
        call [printf]; se afiseaza '*'
        add esp, 4*1; se elibereaza parametrii de pe salva; 4=dimensiunea unui dword 1= numarul de parametrii
        push dword [b]; se pune pe stiva valoarea variabilei b
        push dword format
        call [printf]; se apeleaza functia printf pentru afisare
        add esp, 4*2; se elibereaza parametrii de pe salva; 4=dimensiunea unui dword 2= numarul de parametrii
        push dword egal; se punde pe stiva adresa string-ului egal, nu valoarea sa
        call [printf]; se apeleaza functia printf, afisandu-se '='
        push dword [rezultat]; se pune pe stiva valoarea variabilei rezultat
        push dword format
        call [printf]; se  apeleaza functia printf pentru afisare
        add esp, 4*2; se elibereaza parametrii de pe salva; 4=dimensiunea unui dword 2= numarul de parametrii
       
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program