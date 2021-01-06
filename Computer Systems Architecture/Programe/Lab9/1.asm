bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,fprintf,fread,fclose,fopen,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll 
import fprintf msvcrt.dll 
import printf msvcrt.dll                        ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len equ 100
    l equ 20
    numefis times l db 0
    format db '%s',0
    formatn db '%u',0
    modr db 'r',0
    mesaj db 'Nu s-a gasit fisierul cu numele introdus',0
    handle dd -1
    buffer times len db 0,0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un fisier text. Sa se citeasca continutul fisierului, sa se contorizeze numarul de vocale si sa se afiseze aceasta valoare. 
        
        ;citire nume fisier 
        push dword numefis
        push dword format
        call [scanf]
        add esp,4*2
        
        ;deschidere fisier 
        push dword modr 
        push dword numefis
        call [fopen] 
        add esp,4*2 
        
        cmp eax,0 
        je eroare
        
        mov ebx,0
        mov [handle],eax
        ;citire fisier 
        citeste:
            push dword [handle]
            push dword len 
            push dword 1
            push dword buffer
            call [fread]
            add esp,4*4
            
            mov esi,buffer
            mov ecx,eax 
            push eax 
            jecxz final 
            repet:
                lodsb
                cmp al,'a' 
                je adauga
                cmp al,'A' 
                je adauga
                cmp al,'e'
                je adauga
                cmp al,'E' 
                je adauga
                cmp al,'i'
                je adauga
                cmp al,'I' 
                je adauga
                cmp al,'o'
                je adauga
                cmp al,'O' 
                je adauga
                cmp al,'u'
                je adauga
                cmp al,'U' 
                je adauga                
                jmp fin 
                adauga:
                    add ebx,1
            fin:
            loop repet
                final:
            pop eax 
            cmp eax,len 
            je citeste 
        
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
        
        push ebx 
        push formatn
        call [printf]
        add esp,4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
