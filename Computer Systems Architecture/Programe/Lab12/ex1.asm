bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,fopen,fclose,fread,scanf,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import fopen msvcrt.dll 
import fclose msvcrt.dll 
import fread msvcrt.dll 
import scanf msvcrt.dll
import printf msvcrt.dll   
extern afisare                        ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len equ 100
    s times (len+1) db
    format db '%s',0
    
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Write a program that reads a string of maximum 100 bytes from the keyboard. Determine the number of digits in the string and write a procedure that prints this number in base 16 on the screen.
        push dword s 
        push dword format 
        call [scanf]
        
        mov esi,s
        mov ebx,0
        repeta:
            lodsb
            cmp al,0
            je final
            cmp al,'0'
            jae et
            jmp sf
            et:
                cmp al,'9'
                jbe este_cifra
                jmp sf 
            este_cifra:
                add ebx,1
           sf: 
            jmp repeta
            
        final:
            call afisare 
             
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
