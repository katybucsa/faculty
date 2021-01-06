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
    sir DD 12AB5678h, 1256ABCDh, 12344344h 
    len equ ($-sir)/4
    l equ ($-sir)/4
    format db '%x ',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un sir de dublucuvinte. Sa se ordoneze crescator sirul cuvintelor superioare ale acestor dublucuvinte. Cuvintele inferioare raman neschimbate.
        ;Exemplu:
        ;dandu-se:
        ;sir DD 12AB5678h, 1256ABCDh, 12344344h 
        ;rezultatul va fi
        ;12345678h, 1256ABCDh, 12AB4344h.
        mov esi,sir
        add esi,2
        mov edx,0
        repeta:
            mov edx,1
            mov esi,sir 
            add esi,2
            mov ecx,len
            jecxz final
            parcurge:
                lodsw
                add esi,2
                cmp ax,[esi]
                ja change
                jmp fine 
                change:
                    mov bx,[esi]
                    mov [esi],ax
                    mov [esi-4],bx
                    mov edx,0
            fine:
                cmp ecx,2 
                je final
                loop parcurge
        final:
            cmp edx,0
            jz repeta
                
            mov ecx,l
            mov esi,sir 
            afisare:
                lodsd 
                push ecx
                push eax
                push dword format
                call [printf]
                add esp,4*2
                pop ecx 
            loop afisare
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
