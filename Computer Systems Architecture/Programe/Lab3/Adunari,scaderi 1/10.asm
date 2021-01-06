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
    b dw 400
    c dd 1200
    d dq 5000
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;b+c+d+a-(d+c)=6603-6200=403
        ;a - byte, b - word, c - double word, d - qword
        
        
        mov ax,[b]
        cwd
        push dx
        push ax
        pop eax
        add eax,[c]
        cdq
        add eax,dword[d]
        adc edx,dword[d+4];edx:eax=b+c+d
        mov ebx,eax
        mov ecx,edx
        mov al,[a]
        cbw
        cwd
        push dx
        push ax
        pop eax
        cdq
        add ebx,eax
        adc ecx,edx;ecx:ebx=b+c+d+a
        
        mov eax,[c]
        cdq
        add eax,dword[d]
        adc edx,dword[d+4];edx:eax=d+c
        sub ebx,eax
        sbb ecx,edx
      
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program