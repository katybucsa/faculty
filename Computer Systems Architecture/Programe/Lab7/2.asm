bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    sir DD 12345678h, 1256ABCDh, 12AB4344h
    l equ ($-sir)/2
    l1 equ ($-sir)/4
    a db 0
    format db '%x ',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;2. Se da un sir de dublucuvinte. Sa se ordoneze descrescator sirul cuvintelor inferioare ale acestor dublucuvinte. Cuvintele superioare raman neschimbate.
        ;Exemplu:
        ;dandu-se:
        ;sir DD 12345678h 1256ABCDh, 12AB4344h
        ;rezultatul va fi
        ;1234ABCDh, 12565678h, 12AB4344h.
        
        mov esi,sir 
        mov edx,0
        parcurge:
            mov edx,1
            mov ecx,l
            jecxz final 
            repeta:
                lodsw;in ax
                add esi,2 
                mov bx,ax 
                lodsw 
                add esi,2
                cmp ax,bx 
                ja change
                jmp fine
                change:
                    mov [esi-4],bx 
                    mov [esi-8],ax 
                    mov edx,0
            fine:
            loop repeta 
            final:
                cmp edx,0
                jz parcurge
            mov ecx,l1
            mov esi,sir 
            jecxz finl
        afisare:
            lodsd
            push ecx 
            push eax
            push dword format
            call [printf]
            add esp,4*2
            pop ecx
        loop afisare
        finl:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
