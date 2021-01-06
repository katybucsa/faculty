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
    a dw -1
    b db 2
    c db 95
    e dd 1000
    x dq 1 
    rez dq -1

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        ;a*b-(100-c)/(b*b)+e+x
        ;a-word; b,c-byte; e-doubleword; x-qword
    
    
    
    mov al,100
    sub al,[c];al=100-c
    mov bl,al
    mov al,[b]
    imul byte [b]  ;ax=b*b
    mov cx,ax ; cx=b*b 
    cbw
    cwd  ;dx:ax=100-c
    
    idiv cx  ;ax=(100-c)/(b*b) ,dx=rest
    mov cx,ax
    mov al,[b]
    cbw
    imul word [a]; dx:ax=a*b
    push dx
    push ax
    pop ebx ;ebx=a*b
    mov ax,cx
    cwd  ;dx:ax=(100-c)/(b*b)
    push dx
    push ax
    pop eax
    sub ebx,eax ;ebx=a*b-(100-c)/b*b
    add ebx, dword[e];ebx=a*b-a*b-(100-c)/b*b+e
    mov eax,ebx 
    cdq
    add eax,dword [x]
    adc edx,dword [x+4];edx=eax=a*b-(100-c)/(b*b)+e+x
    mov[rez],eax
    mov [rez+4],edx 
    
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
