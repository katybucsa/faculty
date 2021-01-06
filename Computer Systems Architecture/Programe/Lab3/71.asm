;Bucsa Ecaterina, grupa 211/2, 18.10.2017,laboratorul 3, problema 7
;c-(d+d+d)+(a-b)
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
a dd 7
b dd 4
c dd 5
d dd 1
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov eax,[c];eax=c
    mov ebx,[d];ebx=d
    adc ebx,[d];ebx=d+d
    adc ebx,[d];ebx=d+d+d
    sub eax,ebx;eax=eax-ebx=c-(d+d+d)
    mov ecx,[a];ecx=a
    sub ecx,[b];ecx=ecx-b=a-b
    add eax,ecx;eax=eax+ecx=c-(d+d+d)+(a+b)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
