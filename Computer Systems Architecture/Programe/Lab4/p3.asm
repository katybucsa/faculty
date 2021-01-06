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
    a dw 0111011101010111b
    b dw 1001101110111110b
    c dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se dau cuvintele A si B. Sa se obtina dublucuvantul C:
        ;bitii 0-2 ai lui C coincid cu bitii 12-14 ai lui A
        ;bitii 3-8 ai lui C coincid cu bitii 0-5 ai lui B
        ;bitii 9-15 ai lui C coincid cu bitii 3-9 ai lui A
        ;bitii 16-31 ai lui C coincid cu bitii lui A


        mov ebx,0
        mov ax,[a]
        and ax,0111000000000000b
        mov cl,4
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax

        mov ax,[b]
        and ax,0000000000111111b
        mov cl,3
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax

        mov ax,[a]
        and ax,0000001111111000b
        mov cl,6
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax

        mov ax,[a]
        mov dx,0
        push dx
        push ax
        pop eax
        mov cl,16
        ror eax,cl
        or ebx,eax
        mov[c],ebx


        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
