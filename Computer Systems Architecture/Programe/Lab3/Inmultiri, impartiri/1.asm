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
    ;c+(a*a-b+7)/(2+a)
    ;a-byte; b-doubleword; c-qword
    a db 2
    b dd 5
    c dq 3

; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov al,[a]
    imul byte [a];ax=a*a
    cwd;dx:ax=a*a
    push dx
    push ax
    pop eax;eax=a*a
    sub eax,[b];eax=a*a-b
    add eax,7;eax=a*a-b+7
    push eax
    mov al,[a]
    add al,2
    cbw;ax=2+a
    mov cx,ax
    pop ax
    pop dx;dx:ax=a*a-b+7
    idiv cx;ax=(a*a-b+7)/(2+a)
    cwd;dx:ax=(a*a-b+7)/(2+a)
    push dx
    push ax
    pop eax
    cdq;edx:eax=(a*a-b+7)/(2+a)
    mov ebx,dword [c]
    mov ecx,dword [c+4];ecx:ebx=c
    add ebx,eax
    adc ecx,edx;ecax:ebx=c+(a*a-b+7)/(2+a)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
