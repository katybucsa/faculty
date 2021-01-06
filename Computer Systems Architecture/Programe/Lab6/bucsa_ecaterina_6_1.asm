;Bucsa Ecaterina, grupa 211/2, 8.11.2017,laboratorul 6, problema 1

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import printf msvcrt.dll


; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    s1 db 1,2,3,4
    l_s1 equ $-s1
    s2 db 5,6,7
    l_s2 equ $-s2
    d resb l_s1+l_s2
    test_var db -1
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se dau doua siruri de octeti S1 si S2. Sa se construiasca sirul D prin concatenarea elementelor din sirul S1 1uate de la stanga spre dreapta ;si a elementelor din sirul S2 luate de la dreapta spre stanga. 
        ;Exemplu:
        ;S1: 1, 2, 3, 4
        ;S2: 5, 6, 7
        ;D: 1, 2, 3, 4, 7, 6, 5
        
        mov ecx,l_s1; punem lungimea sirului s1 in ecx pentru a realiza bucla loop de ecx ori
        jecxz final_prog
        mov esi,0; registrul esi primeste valoarea 0 pentru a parcurge sirul s1 de la stanga la dreapta
        mov edi,0
        repeta:
            mov al,[s1+esi]
            mov [d+edi],al
            inc esi
            inc edi
        loop repeta
        final_prog:
        
        
        mov ecx,l_s2;punem lungimea sirului s2 in ecx pentru a realiza bucla loop de ecx ori
        jecxz final_prog1
        mov esi,l_s2-1;registrul esi primeste valoarea lungimii sirului s2 -1 pentru a parcurge sirul s1 de la dreapta la stanga
        parcurge:
            mov al,[s2+esi]
            mov [d+edi],al
            sub esi,1
            inc edi
        loop parcurge
        final_prog1:
   
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
        
        
        
        
        
        
