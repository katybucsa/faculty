bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,fprintf,printf,fread,fopen,fclose,rename,remove               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll
import remove msvcrt.dll
import rename msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len equ 100 
    l equ 20
    nume1 times l db 0,0
    nume2 db 'exm.txt',0
    handle1 dd -1
    handle2 dd -1
    modr db 'r',0
    modw db 'w',0
    mesaj db 'Eroare deschidere fisier',0
    buffer times len db 0,0
    format db '%s',0
    formatnr db '%u',0
    nr db 0
    
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword nume1
        push dword format
        call [scanf] 
        add esp,4*2 
        
        push dword nr
        push dword formatnr
        call [scanf]
        add esp,4*2
        
        push dword modr 
        push dword nume1 
        call [fopen]
        add esp,4*2
        
        cmp eax,0 
        je eroare
        mov [handle1],eax 
        
        push dword modw
        push dword nume2
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je eroare
        mov [handle2],eax 
        
        repet:
            push dword [handle1]
            push dword len 
            push dword 1
            push dword buffer
            call [fread]
            add esp,4*4
            
            mov esi,buffer
            mov edi,buffer 
            mov ecx,eax 
            jecxz final
            parcurge:
                lodsb 
                cmp al,'A'
                jl fine 
                cmp al,'z'
                ja fine
                cmp al,'Z'
                jle bun1 
                cmp al,'a'
                jae bun2 
                stosb 
                jmp fine 
                
                bun1:
                    add al,[nr]
                    cmp al,'Z'
                    ja scade1 
                    stosb
                    jmp fine 
                 
                bun2:
                    add al,[nr]
                    cmp al,'z'
                    ja scade2
                    stosb
                    jmp fine 
                    
                scade1:
                    sub al,26
                    cmp al,'Z'
                    ja scade1
                    stosb
                    jmp fine 
                
                scade2:
                    sub al,26
                    cmp al,'z'
                    stosb
                    mov [esi-1],al 
                     
            fine:
            loop parcurge 
            final:
                
                push dword buffer
                push dword [handle2]
                call [fprintf]
                add esp,4*2 
                
                cmp eax,len 
                je repet 
                
                push dword[handle1]
                call  [fclose]
                add esp,4
                
                push dword[handle2]
                call [fclose]
                add esp,4
                
                push dword nume1 
                call [remove]
                
                push dword nume1
                push dword nume2 
                call[rename]
                add esp,4*2 
                
                jmp fin
                
                
            
        
        eroare:
            push dword mesaj
            push dword format
            call [printf]
            add esp,4*2
        fin:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
