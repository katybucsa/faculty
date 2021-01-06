;Bucsa Ecaterina, grupa 211/2, 24.10.2017,laboratorul 4, problema 8

;Se dau doua cuvinte A si B. Sa se obtina un octet C care are:
; pe bitii 0-5 bitii 5-10 ai cuvantului A,
; iar pe bitii 6-7 bitii 1-2 ai cuvantului B. 
;Sa se obtina dublucuvantul D care are:
; pe bitii 8-15 bitii lui C,
; pe bitii 0-7 bitii 8-15 din B,
; pe bitii 24-31 bitii 0-7 din A, 
;iar pe bitii 16-23 bitii 8-15 din A

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
    c db 0
    d dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov bl,0;
        mov ax,[a]
        and ax,0000011111100000b
        mov cl,5
        ror ax,cl
        or bl,al
        
        mov ax,[b]
        and ax,0000000000000110b
        mov cl,5
        rol ax,cl
        or bl,al
        mov [c],bl
        
        mov ebx,0
        mov ax,[b]
        cwd
        push dx
        push ax
        pop eax
        and eax,00000000000000001111111100000000b
        mov cl,8
        ror eax,cl
        or ebx,eax
        
        mov al,[c]
        cbw
        cwd
        push dx
        push ax
        pop eax
        and eax,00000000000000000000000011111111b
        mov cl,8
        rol eax,cl
        or ebx,eax
        
        mov ax,[a]
        cwd
        push dx
        push ax
        pop eax
        and eax,00000000000000001111111100000000b
        mov cl,8
        rol eax,cl
        or ebx,eax
        
        mov ax,[a]
        cwd
        push dx
        push ax
        pop eax
        and eax,00000000000000000000000011111111b
        mov cl,8
        ror eax,cl
        or ebx,eax
        mov [d],ebx
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
