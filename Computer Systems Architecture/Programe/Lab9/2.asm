bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,fopen,fread,fclose               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll 
import scanf msvcrt.dll 
import fopen msvcrt.dll 
import fread msvcrt.dll 
import fclose msvcrt.dll                           ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    l equ 20
    len equ 100
    numefis times l db 0
    format db '%s',0
    formatn db '%u',0
    modr db 'r',0
    mesaj db 'Nu s-a gasit numele fisierului introdus',0
    handle dd -1 
    buffer times len db 0,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un fisier text. Sa se citeasca continutul fisierului, sa se contorizeze numarul de consoane si sa se   afiseze aceasta valoare. 
        push dword numefis
        push dword format
        call [scanf]
        add esp,4*2
        
        push dword modr
        push dword numefis
        call [fopen]
        add esp,4*2 
        
        
        cmp eax,0 
        je eroare
        
        mov ebx,0
        mov [handle],eax
        repet:
            push dword [handle]
            push dword len
            push dword 1
            push dword buffer
            call [fread]
            add esp,4*4
            
            push eax 
            mov esi,buffer 
            mov ecx,eax 
            jecxz final
            repeta:
                lodsb 
                cmp al,'a'
                je fin
                cmp al,'e'
                je fin
                cmp al,'i'
                je fin
                cmp al,'o'
                je fin
                cmp al,'u'
                je fin
                cmp al,'A'
                je fin
                cmp al,'E'
                je fin
                cmp al,'I'
                je fin
                cmp al,'O'
                je fin
                cmp al,'U'
                je fin
                cmp al,'A'
                jb fin 
                cmp al,'z'
                ja fin 
                cmp al,'Z'
                jbe ada
                cmp al,'a'
                jae ada 
                jmp fin 
                ada:
                    add ebx,1
                
            fin:
            loop repeta
            final:
            pop eax 
            cmp eax,len
            je repet
        push dword [handle]
        call [fclose]
        add esp,4*2
        jmp fine
        
        eroare:
            push dword mesaj 
            push dword format
            call [printf]
            add esp,4*2 
        fine: 
            push ebx 
            push dword formatn
            call [printf]
            add esp,4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
