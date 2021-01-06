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
    b db 10011011b
    c dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se de cuvantul A si octetul B. Sa se obtina dublucuvantul C astfel:
        ;bitii 0-3 ai lui C coincid cu bitii 6-9 ai lui A
        ;bitii 4-5 ai lui C au valoarea 1
        ;bitii 6-7 ai lui C coincid cu bitii 1-2 ai lui B
        ;bitii 8-23 ai lui C coincid cu bitii lui A
        ;bitii 24-31 ai lui C coincid cu bitii lui B
        
        
        
        mov ebx,0
        mov ax,[a]
        and ax,0000001111000000b
        mov cl,6
        ror ax,cl
        or ax,0000000000110000b
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        mov al,[b]
        and al,00000110b
        mov cl,5
        rol al,cl 
        mov ah,0
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
        mov cl,8
        rol eax,cl
        or ebx,eax
        
        mov al,[b]
        mov ah,0
        mov dx,0
        push dx
        push ax
        pop eax
        mov cl,8
        ror eax,cl
        or ebx,eax
        
        mov[c],ebx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
