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
        ;Se dau doua cuvinte A si B. Sa se obtina dublucuvantul C:
        ;bitii 0-4 ai lui C au valoarea 1
        ;bitii 5-11 ai lui C coincid cu bitii 0-6 ai lui A
        ;bitii 16-31 ai lui C au valoarea 0000000001100101b
        ;bitii 12-15 ai lui C coincid cu bitii 8-11 ai lui B
        
        
        mov ebx,0
        or ebx,00000000000000000000000000011111b
        
        mov ax,[a]
        and ax,0000000001111111b
        mov cl,5
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        mov ax,[b]
        and ax,0000111100000000b
        mov cl,4
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        or ebx,10100110000000000000000000000000b
        
        mov[c],ebx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
