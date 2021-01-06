bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...  a - byte, b - word, c - double word, d - qword
    ;c-(a+d)+(b+d)
    a db 9
    b dw 3
    c dd 14
    d dq 5

; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov al,[a];al=a
    cbw;ax=a
    cwd;dx:ax=al
    push dx
    push ax
    pop eax;
    cdq;edx:eax=a
    add eax,dword[d]
    adc edx,dword[d+4];edx:eax=a+d
    mov ecx,edx
    mov ebx,eax;ecx:ebx=a+d
    mov eax,[c];eax=c
    cdq;edx:eax=c
    sub eax,ebx
    sbb edx,ecx;edx:eax=c-(a+d)
    mov ecx,edx
    mov ebx,eax;ecx:ebx=c-(a+d)
    mov ax,[b];ax=b
    cwd;dx:ax=b
    push dx
    push ax
    pop eax;eax=b
    cdq;edx:eax=b
    add eax,dword[d]
    adc edx,dword[d+4];edx:eax=b+d
    add ebx,eax
    adc ecx,edx;ecx:ebx=c-(a+d)+(b+d)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
