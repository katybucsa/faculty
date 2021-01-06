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
    sir dd 13, 20, 45
    l equ ($-sir)/4
    c times l dd 0
    doi db 2
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov esi,sir
        cld
        mov ecx,l
        jecxz final
        mov edi,c
        repeta:
            mov edx, [esi]
            mov bx, 32
            mov ax,0
            repeta1:
                cmp bx,0
                jz sfarsit
                shl edx,1
                sub bx,1
                jc eticheta
              
             eticheta:
                add ax,1
            sfarsit:
                div byte[doi]
                cmp ah,0
                jz eticheta1
                
             eticheta1:
                movsd
        loop repeta
        final:
             
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
