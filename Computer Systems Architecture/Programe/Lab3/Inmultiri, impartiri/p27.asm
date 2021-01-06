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
    a db 250
    b db 3
    c dw 300
    e dd 1000
    x dq 5000
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(100+a+b*c)/(a-100)+e+x/a=1250/150+1000+20=8+1020=1028
        ;a,b-byte; c-word; e-doubleword; x-qword
        
        mov al,[b]
        cbw
        mov dx,[c]
        imul dx;dx:ax=b*c
        mov bx,ax
        mov cx,dx;cx:bx=b*c
        mov al,[a]
        cbw
        cwd
        add bx,ax
        adc cx,dx
        add bx,100;cx:bx=100+a+b*c
        mov al,[a]
        sub al,100
        cbw
        idiv ax;ax=(100+a+b*c)/(a-100)
        cwd
        push dx
        push ax
        pop ebx;ebx=(100+a+b*c)/(a-100)
        mov al,[a]
        cbw
        cwd
        push dx
        push ax
        pop eax
        mov ecx,eax
        mov eax,dword[x]
        mov edx,dword[x+4]
        idiv ecx;eax=x/a
        add eax,[e];eax=e+x/a
        add ebx,eax
        
        
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
