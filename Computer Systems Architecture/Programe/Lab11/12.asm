;Bucsa Ecaterina, grupa 211/2,laboratorul 11, problema 12

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                         ; msvcrt.dll contains exit, printf and all the other important C-runtime       specific functions

extern intercalare
; our data is declared here (the variables needed by our program)
segment data use32 
    ; ...
    s1 db "abcdefgh123",0
    s2 db "ijklmnop456",0
    len equ $-s2-1
    d1 resb len*2+1; sirul care va memora intercalarea celor 2 siruri, caracterele din primul sir pe pozitii pare 
    d2 resb len*2+1; sirul care va memora intercalarea celor 2 siruri, caracterele din primul sir pe pozitii impare
    format db "%s",10,13,0
; our code starts here
segment code use32 public code
    start:
        ; ...
        ;Se dau doua siruri de caractere de lungimi egale. Se cere sa se calculeze si afiseze rezultatele intercalarii literelor, pentru cele doua intercalari posibile (literele din primul sir pe pozitii pare, si literele din primul sir pe pozitii impare).
        
        
        mov esi,s1; elementele din s1 se vor afla pe pozitii pare in d1
        mov edi,d1
        mov eax, len
        mov edx,0
        push eax
        call intercalare
        
        mov esi,s2; elementele din s2 se vor afla pe pozitii impare in d1
        mov edi,d1 
        inc edi 
        mov eax, len
        mov edx,1
        push eax
        call intercalare
        
        push dword d1
        push dword format
        call [printf]; se afiseaza sirul d1
        add esp,4*2; se elibereaza stiva
        
        
        
        mov esi,s2; caracterele din s2 se vor afla pe pozitii pare 
        mov edi,d2
        mov eax, len
        mov edx,0
        push eax
        call intercalare
        
        mov esi,s1; caracterele din s1 se vor afla pe pozitii impare 
        mov edi,d2
        inc edi 
        mov eax, len
        mov edx,1
        push eax
        call intercalare
        
        
        push dword d2
        push dword format
        call [printf]; se afiseaza sirul d2
        add esp,4*2; se elibereaza stiva 
      ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
