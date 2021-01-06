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
    a db 01110111b
    b db 10011011b
    c dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se dau octetii A si B. Sa se obtina dublucuvantul C:
        ;bitii 16-31 ai lui C sunt 1
        ;bitii 0-3 ai lui C coincid cu bitii 3-6 ai lui B
        ;bitii 4-7 ai lui C au valoarea 0
        ;bitii 8-10 ai lui C au valoarea 110
        ;bitii 11-15 ai lui C coincid cu bitii 0-4 ai lui A
        ; exit(0)
        
        mov ebx,0
        mov al,[b]
        and al,01111000b
        mov cl,3
        ror al,cl
        mov ah,0
        mov dx,0
        push dx
        push ax
        pop eax
        or ebx,eax
        
        and ebx,11111111111111111111111100001111b
        or ebx,00000000000000000000001100000000b
        
        mov al,[a]
        and al,00011111b
        mov ah,0
        mov dx,0
        push dx
        push ax
        pop eax
        mov cl,11
        rol eax,cl
        or ebx,eax
        
        or ebx,11111111111111110000000000000000b
        
        mov [c],ebx
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
