;Se citeste de la tastatura un nume de fisier si un nr n.Fisierul contine cuvinte separate prin spatiu.Se se scrie in out.txt doar cuvintele a caror lungime este egala cu n

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,fprintf,fopen,fread,fclose,printf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll
import fprintf msvcrt.dll
import fread msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll
                 ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    len equ 2000
    l equ 20
    numein times l db 0,0
    numeout db 'out.txt',0
    n db 0
    format1 db '%s',0
    format2 db '%u',0
    mesaj db 'Deschiderea fisierului nu a reusit',0
    handle1 dd -1
    handle2 dd -1
    modr db 'r',0
    modw db 'w',0
    cuv times 30 db 0,0
    
    buffer times len db 0,0
    s dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword numein
        push dword format1
        call [scanf]
        add esp,4*2
        
        push dword n 
        push dword format2
        call [scanf]
        add esp,4*2
        
        push dword modr
        push dword numein 
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je eroare 
        
        mov [handle1],eax 
        
        push dword modw
        push dword numeout
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je eroare
        
        mov [handle2],eax 
        
        mov edi,cuv
        repet:
            push dword [handle1]
            push dword len
            push dword 1
            push dword buffer
            call [fread]
            add esp,4*4 
            
            mov esi,buffer  
            mov ebx,0 
            mov ecx,eax 
            mov [s],eax  
            jecxz final
            parcurge:
                lodsb
                cmp al,' '
                je compara
                stosb 
                add ebx,1
                jmp loop1
                
                
                compara:
                    mov edx,[n]
                    push ecx
                    cmp ebx,edx 
                    jne f
                    
                    
                    push dword cuv 
                    push dword[handle2]
                    call [fprintf]
                    add esp,4*2
                     
                
                f:
                   mov ecx,ebx 
                   mov edi,cuv  
                   jecxz fine
                   zero:    
                    mov byte [edi],0
                    inc edi 
                   loop zero
                   fine:
                   mov ebx,0
                   mov edi,cuv
                    pop ecx 
                    
                    
                    
            loop1:       
            loop parcurge
            final:
            mov eax,[s]
            cmp eax,len 
            je repet
           
        push dword [handle1]
        call [fclose]
        add esp,4
        
        push dword [handle2]
        call [fclose]
        add esp,4
        
        jmp fin 
        
        
        
        
        
        eroare:
            push dword mesaj
            push dword format1
            call [printf]
            add esp,4*2
        fin:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
