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
    a dw 1234h,5678h,90h
    l_a equ ($-a)/2
    b dw 4h,0abcdh,10h,1122h
    l_b equ ($-b)/2
    s db 0
    c times l_a+l_b db 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Given two strings of words, a and b, obtain a third string c in the following way: concatenate the string of the least significant bytes from the words of the first string with the string of most significant bytes of the words from the second string. String c must be ordered ascending, bytes are signed numbers.
        ;Example: let the two string be defined as
        ;a dw 1234h,5678h,90h
        ;b dw 4h,0abcdh,10h,1122h
        ;c times 7 db 0
        ;These two strings will be represented in memory:
        ;a -> | 34h| 12h| 78h| 56h| 90h| 00h|
        ;b -> | 04h| 00h| 0cdh| 0abh| 10h| 00h| 22h| 11h|
        ;By concatenating, as required, string c will be:
        ;34h,78h,90h,00h,0abh,00h,11h. After concatenating, ;string c must be ordered ascending.
        
        mov ecx,l_a
        jecxz final_prog
        mov esi,a
        mov ebx,0
        mov edi,c
        repeta:
            movsb
            inc esi
            add ebx,1
        loop repeta
        final_prog:
        mov ecx,l_b
        jecxz final_pr
        mov esi,b
        repeta1:
            inc esi
            movsb
            add ebx,1
        loop repeta1
        final_pr:
            
            sub ebx,1
            mov esi,c
            mov ecx,ebx 
            mov edx,1
            jecxz fin
            parcurge:
                lodsb 
                cmp al,[esi]
                ja schimba
                jmp fine
                schimba:
                    mov dl,[esi]
                    mov [esi],al 
                    mov [esi-1],dl 
                    mov edx,0 
            fine:
            loop parcurge 
            fin:
                cmp edx,0
                je final_pr
                
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
