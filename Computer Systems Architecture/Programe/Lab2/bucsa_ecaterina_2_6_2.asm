;Bucsa Ecaterina, grupa 211/2, laboratorul 2, problema 6
;c-(d+a)+(b+c)
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
a db 3
b db 7
c db 10
d db 2
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov AX, [c];AX=c
    mov BX,[d];BX=d
    add BX, [a];BX=BX+[a]
    sub AX, BX;AX=AX-BX
    mov CX, [b];CX=b
    add CX, [c];CX=CX+[c]
    add AX, CX;AX=AX+CX
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
