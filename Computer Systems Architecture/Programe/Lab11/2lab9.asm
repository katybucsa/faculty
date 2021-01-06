bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll 
import printf msvcrt.dll                         ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    n dd 0
    format db '%x',0
    mesaj  db 'Numar invalid',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Read a number from keyboard, in hexadecimal format. Validate that it is between 0 and 0xFFFF (it should be represented ;on a word). If it's not, display a message and read the number again. Open a file named "in.txt", which should contain ;exactly 16 bytes. Read them, and then print on the screen only those characters whose corresponding bit index in the ;number read from the keyboard is set.

        ;Example: 
        ;number read: F2A1 
        ;in.txt: 0123456789abcdef
        ;output: 0579cdef
        
        citeste:
            push dword n
            push dword format
            call [scanf]
            add esp,4*2
            
            mov eax,[n]
            cmp eax,0
            jg et
            jmp sf
            et:
                cmp eax,0ffffh
                jl et1
                
            sf:
                push dword mesaj
                call[printf]
                add esp,4*1
                jmp citeste
            
            et1:
                mov eax,[n]
                
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
