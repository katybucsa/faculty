bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,fread,fopen,fclose              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import fclose msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll 
import printf msvcrt.dll 
import fread msvcrt.dll 
import fopen msvcrt.dll                           ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len equ 20
    l_r equ 100
    format1 db '%u',0
    mesaj db "Nu s-a putut crea fisierul",0
    format db "%s",0
    nume_fisier times len db 0
    buffer times l_r+1  db 0
    format_s db "%s",0
    handle_1 dd -1
    modr db "r",0
    cuv db 0,0
    
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword nume_fisier
        push dword format_s
        call [scanf]
        add esp, 4*2
        push dword cuv
        push dword format_s
        call [scanf]
        add esp,4*2
        push dword modr
        push dword nume_fisier
        call [fopen]
        add esp,4*2
        
        mov ebx,0
            
        
        cmp eax,0
        je eroare_fisier
        mov edi,cuv
        mov [handle_1],eax
        repeta:
            push dword [handle_1]
            push dword l_r
            push dword 1
            push dword buffer
            call [fread]
            add esp, 4*4
            
            mov esi,buffer
            push eax
            repeta1:
                cmpsb 
                jne et
                jmp fin 
                et:
                    mov edi,cuv
                fin:
                    cmp byte [edi],0
                    jz adauga
                    cmp byte [esi],0
                    jz final1
                    jmp repeta1
                    adauga:
                        add ebx,1
                        mov edi,cuv
                        jmp repeta1
                        
                final1:
                pop eax 
                cmp eax,l_r
                je repeta
            push dword [handle_1]
            call [fclose]
            add esp,4
            jmp ext    
                
           
            
        eroare_fisier:
            push dword mesaj
            push dword format
            call [printf]
            add esp, 4*2
        ext:
            push ebx 
            push dword format1
            call [printf]
            add esp,4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
