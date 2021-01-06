;Bucsa Ecaterina, grupa 211/2, 6.11.2017, tema laborator 5, problema 10

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf,scanf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll 
import printf msvcrt.dll
import scanf msvcrt.dll   ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    n dd 0
    mesaj1 db "n=",0
    format_s db "%d",0
    mesaj2 db "Numarul ",0
    mesaj3 db " in baza 16 este:",0
    format_p db "%x",0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Sa se citeasca de la tastatura un numar in baza 10 si sa se afiseze valoarea acelui numar in baza 16.
        ;Exemplu: Se citeste: 28; se afiseaza: 1C
        
        
        push dword mesaj1; se pune pe stiva adresa stringului mesaj1
        call [printf]; se afiseaza pe ecran mesaj1
        add esp,4*1; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 1-numarul de parametrii
        
        push dword n; se pune pe stiva adresa lui n
        push dword format_s;
        call [scanf]; se apeleaza functia scanf pentru a citi n
        add esp,4*2; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 2-numarul de parametrii
        
        push dword mesaj2; se pune pe stiva adresa stringului mesaj2
        call [printf]; se afiseaza pe ecran mesaj2
        add esp,4*1; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 1-numarul de parametrii
        
        push dword [n]; se pune pe stiva valoarea lui n
        push dword format_s
        call [printf]; se apeleaza functia printf pentru a afisa valoare lui n
        add esp,4*2; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 2-numarul de parametrii
        
        push dword mesaj3; se pune pe stiva adresa stringului mesaj3
        call [printf]; se afiseaza pe ecran mesaj3
        add esp,4*1; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 1-numarul de parametrii
        
        
        push dword [n]; se pune pe stiva valoarea lui n
        push dword format_p
        call [printf]; se afiseaza valoarea lui n in baza 16
        add esp,4*2; se elibereaza parametrii de pe stiva; 4-dimensiunea unui dword; 2-numarul de parametrii
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
