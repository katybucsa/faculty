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
        ;Se dau cuvintele A si B. Se cere dublucuvantul C:
        ;bitii 0-3 ai lui C coincid cu bitii 5-8 ai lui B
        ;bitii 4-8 ai lui C coincid cu bitii 0-4 ai lui A
        ;bitii 9-15 ai lui C coincid cu bitii 6-12 ai lui A
        ;bitii 16-31 ai lui C coincid cu bitii lui B
        
        mov ebx,0
        mov ax,[b]
        and ax,0000000111100000b
        mov cl,5
        ror ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        mov ax,[a]
        and ax,0000000000011111b
        mov cl,4
        rol ax,cl
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        mov ax,[a]
        and ax,0001111111000000b
        mov cl,3
        rol ax,cl
        mov dx,0
        push dx 
        push ax 
        pop eax
        or ebx,eax
        
        mov ax,[b]
        mov dx,0
        push dx 
        push ax 
        pop eax
        mov cl,16
        rol eax,cl
        or ebx,eax
        
        mov[c],ebx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
