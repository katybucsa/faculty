;Bucsa Ecaterina, grupa 211/2, 18.10.2017,laboratorul 3, problema 17
;x-(a*a+b)/(a+c/a)
;a,c-byte; b-doubleword; x-qword
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
b dd 7
c db 3
x dq 12
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov al,[a];al=a
    imul byte [a];ax=a*a
    cwd;dx:ax=a*a
    push dx
    push ax
    pop ebx;ebx=a*a
    mov eax,[b];eax=b
    add eax,ebx;eax=eax+ebx=a*a+b
    mov ebx,eax;ebx=a*a+b
    mov al,[c];al=c
    cbw;ax=c
    mov cl,[a];cl=a
    idiv cl;al=ax/cl=c/a ah=ax%cl
    add al,[a];al=al+a=a+c/a
    cbw;ax=a+c/a
    mov cx,ax;cx=ax=a+c/a
    mov eax,ebx;eax=a*a+b
    idiv cx;ax=eax/cx=(a*a+b)/(a+c/a); dx=eax%cx
    push dx
    push ax
    pop ebx;ebx=(a*a+b)/(a+c/a)
    cdq;edx:ebx=(a*a+b)/(a+c/a)
    mov eax,dword [x]
    adc ecx,dword [x+4];ecx:eax=x
    sub eax,ebx;
    sbb ecx,edx;ecx:eax=x-(a*a+b)/(a+c/a)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
