;Bucsa Ecaterina, grupa 211/2, 10.10.2017,laboratorul 2, problema 6
;(a+b)-(a+d)+(c-a)
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
a db 5
b db 3
c db 10
d db 1
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov AX,[a];AX = a
    add AX, [b];AX=AX+[b]=a+b
    mov BX, [a];BX=a
    add BX, [d];BX=BX+[d]=a+d
    sub AX, BX;AX=AX-BX=(a+b)-(a+d)
    mov CX, [c];CX=c
    sub CX,[a];CX=CX-[a]=c-a
    add AX,CX;AX=AX+CX=(a+b)-(a+d)+(c-a)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
