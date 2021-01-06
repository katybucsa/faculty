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
    a db 01110111b
    b db 10011011b
    c db 01010111b
    d db 10111110b
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Dandu-se 4 octeti, sa se obtina in AX suma numerelor intregi reprezentate de bitii 4-6 ai celor 4 octeti.
        
        
        mov bx,0
        mov al,[a]
        and al,01110000b
        mov cl,4
        ror al,cl
        mov ah,0
        or bx,ax
        
        mov al,[b]
        and al,01110000b
        mov cl,4
        ror al,cl
        mov ah,0
        adc bx,ax
        
        mov al,[c]
        and al,01110000b
        mov cl,4
        ror al,cl
        mov ah,0
        adc bx,ax
        
        mov al,[d]
        and al,01110000b
        mov cl,4
        ror al,cl
        mov ah,0
        adc bx,ax
        
        mov ax,bx
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
