bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,fprintf,fopen,fclose,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll 
import fprintf msvcrt.dll 
import fopen msvcrt.dll 
import fclose msvcrt.dll
import printf msvcrt.dll                           ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    l equ 20
    nume db 'ads.txt',0
    format db '%s',0
    handle dd -1
    moda db 'a',0
    cuv times l db 0,0
    mesaj db 'Nu s-a putut creea fisierul',0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un nume de fisier (definit in segmentul de date). Sa se creeze un fisier cu numele dat, apoi sa se citeasca de la tastatura cuvinte si sa se scrie in fisier cuvintele citite pana cand se citeste de la tastatura caracterul '$'.
        push dword moda
        push dword nume
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je eroare
        
        mov [handle],eax 
        repet:
            push dword cuv 
            push dword format
            call [scanf]
            add esp,4*2 
            mov ebx,[cuv] 
            cmp ebx,'a'
            je fine
            
            mov edi,
            push dword cuv
            push dword [handle]
            call [fprintf]
            add esp,4*2
            mov edx,0
            mov [cuv],edx 
            jmp repet
        
        
        
        
        
        eroare:
            push dword mesaj 
            push dword format
            call [printf]
            add esp,4*2
        fine:    
            push dword [handle]
            call [fclose]
            add esp,4
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
