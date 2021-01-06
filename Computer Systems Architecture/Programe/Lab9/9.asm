bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,fopen,fclose,fread,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll  
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll
import printf msvcrt.dll  ; exit is a function that ends the calling process. It is         defined in msvcrt.dll
                                    ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    nume_fis db 'citire.txt',0
    mod_acces db 'r',0
    descriptor_fis dd -1
    nr_car_citite dd 0
    len equ 1
    a resb len
    format db 'Caracterul %c apare de %d ori',0
    ap dd 0
    el dd 0
    len_c dd 0
    c  db 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un fisier text. Sa se citeasca continutul fisierului, sa se determine caracterul special (diferit de litera) cu ;cea mai mare frecventa si sa se afiseze acel caracter, impreuna cu frecventa acestuia. 
        ;Numele fisierului text este definit in segmentul de date.
        
        push dword mod_acces
        push dword nume_fis
        call [fopen]
        add esp, 4*2
        
        mov [descriptor_fis],eax
        
        cmp eax,0
        je final
        
        
        mov edi,0
        bucla:
            push dword [descriptor_fis]
            push dword len
            push dword 1
            push dword a
            call [fread]
            add esp, 4*4
            
                   
            
            cmp eax, 0
            je cleanup
            
            mov bl,[a]
            
            cmp bl,'A'
            jl adauga
            jmp et
            
            et:
            cmp bl,'z'
            jg adauga
            jmp et1
            
            et1:
            cmp bl,'a'
            jl et2
            jmp sfarsit
            
            et2:
               cmp bl,'Z'
               jg adauga
               jmp sfarsit
            
            adauga:
                mov [c+edi],bl
                inc edi
                jmp sfarsit
                
            sfarsit:   
                mov [nr_car_citite],eax
            
            jmp bucla
            
        cleanup:
            push dword [descriptor_fis]
            call [fclose]
            add esp, 4
            mov [len_c],edi
            
        final:
            mov bl,0
            mov esi,c
            cld
            mov ecx,[len_c]
            jecxz finalp
            repeta:
                lodsb
                mov dl,0
                mov edi,[len_c]
                dec edi
                repeta1:
                    cmp al,[c+edi]
                    je et3
                    jmp sfarsit1
                
                et3:
                    add dl,1
                    cmp dl,bl
                    jg et4
                    jmp sfarsit1
            
                et4:
                    mov bl,dl
                    mov [el],al
                    jmp sfarsit1
            
                sfarsit1:
                    dec edi
                    cmp edi,-1
                    je finalp1
                    
                    jmp repeta1
            
                finalp1:
                    loop repeta
                finalp:
                    mov [ap],bl
                    push dword [ap]
                    push dword [el]
                    push dword format
                    call [printf]
                    add esp,4*3
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
