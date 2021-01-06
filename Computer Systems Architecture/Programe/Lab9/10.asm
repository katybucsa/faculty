bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,fprintf,fopen,fread,fclose               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll
import fread msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    l equ 30
    len equ 100
    sir times 120 db 0,0
    modw db 'w',0
    handle db -1
    buffer times len db 0,0 
    numefis times l db 0,0
    format db '%s',10,32,0
    mesaj db 'Nu s-a putut creea fisierul',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Sa se citeasca de la tastatura un nume de fisier si un text. Sa se creeze un fisier cu numele dat in directorul curent si sa se scrie textul in acel fisier. 
        ;Observatii: Numele de fisier este de maxim 30 de caractere. Textul este de maxim 120 de caractere.
        push dword numefis
        push dword format
        call [scanf]
        add esp,4*2
        
        push dword sir 
        push dword format
        call[scanf]
        add esp,4*2
        
        push dword modw
        push dword numefis
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je eroare
        
        mov [handle],eax
        
        push dword sir
        push dword [handle]
        call [fprintf]
        add esp,4*2
        
        push dword [handle]
        call [fclose]
        add esp,4
        jmp fine
        
        eroare:
            push dword mesaj 
            push dword format
            call [printf]
            add esp,4*2
        fine:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
