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
    s1 db '123ab','c','d','e'
    s2 dw '123ab','c','d','e'
    l_s1 equ s2-s1
    d resb l_s1
    test_var db -1
    d1 times l_s1 db -1;rezerva spatiu in memorie pentru d1 stocand valoarea -1 de l_s1 ori
    test_var1 db -1
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov ecx,l_s1
        mov esi,0
        parcurge:
            mov al,[s1+esi]
            inc esi
            loop parcurge
        
        ;for_1:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
