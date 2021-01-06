;Bucsa Ecaterina, grupa 211/2, 18.10.2017,laboratorul 3, problema 7
;a,b,c,d - qword
;(c+c+c)-b+(d-a)
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
a dq -2
b dq 3
c dq -4
d dq 25
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov eax,dword[c]
    mov edx,dword [c+4];edx:eax=c
    add eax,dword [c]
    adc edx,dword [c+4];edx:eax=c+c
    add eax,dword [c]
    adc edx,dword [c+4];edx:eax=c+c+c
    mov edx,0
    sub eax,dword [b]
    sbb edx,dword [b+4]; edx:eax=(c+c+c)-b
    mov ebx,dword [d]
    adc ecx,dword [d+4];ecx:ebx=d
    sub ebx,dword[a]
    sbb ecx,dword[a+4];ecx:ebx=d-a
    add eax,ebx
    adc edx,ecx;edx:eax=ecx:ebx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
