;Bucsa Ecaterina, grupa 211/2, laboratorul 2, problema 6
;[2*(a+b)-5*c]*(d-3)

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
a db 2
b db 4
c db 2
d dw 5
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov AL,[a];AL=a
    add AL,[b];AL=AL+[b]=a+b
    mov AH,2;AH=2
    mul AH;AX = AH * AL=2*(a+b)
    mov BX,AX;BX=AX=2*(a+b)
    mov AL,[c];AL=c
    mov BH,5;BH=5
    mul BH;AX=BH*AL=5*c
    sub BX,AX;BX=BX-AX=2*(a+b)-5*c
    mov AX,BX;AX=BX=2*(a+b)-5*c
    mov CX,[d];CX=d
    sub CX,3;CX=CX-3=d-3
    mul CX;DX:AX=AX*CX=[2*(a+b)-5*c]*(d-3)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
