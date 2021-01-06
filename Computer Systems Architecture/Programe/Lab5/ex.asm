bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                        ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import scanf msvcrt.dll 
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    n dd 0
    format_s db "%d",0
    mesaj db "nr introdus este:",0
    format_p db "%d",10,13,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword n
        push dword format_s
        call [scanf]
        add esp,4*2;2 de la 2 push,4 pt ca 
        
        push dword mesaj
        call [printf]
        add esp,4*1
        push dword [n]
        push dword format_p
        call [printf]
        call [printf]
        add esp,4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
