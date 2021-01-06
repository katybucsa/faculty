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
    s1 db 'abcde',0
    l1 equ $-s1
    s2 db 'acdmp',0
    l2 equ $-s2
    s3 times l1+l2 db 0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;1. Se dau doua siruri de caractere ordonate alfabetic s1 si s2. Sa se construiasca prin interclasare sirul    ordonat s3 care sa contina toate elementele din s1 si s2.
        mov esi,s1 
        mov edx,s2
        mov edi,s3
        repet:
            lodsb 
            mov bl,al 
            push esi 
            mov esi,edx 
            lodsb 
            cmp al,bl 
            jb adauga1 
            jmp adauga2
            adauga1:
                stosb
                cmp byte [esi],0 
                jz final
                mov edx,esi 
                pop esi 
                dec esi
                jmp repet
            adauga2:
                dec esi 
                mov edx,esi 
                pop esi 
                mov al,bl 
                stosb
                cmp byte [esi],0
                jz final1
                jmp repet
        final:
            pop esi 
            dec esi
            repeta:
                movsb 
                cmp byte [esi],0
                jnz repeta
                jmp fine
        final1:
            mov esi,edx 
            repeta1:
                movsb 
                cmp byte [esi],0
                jnz repeta1
                jmp fine
        fine:
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
