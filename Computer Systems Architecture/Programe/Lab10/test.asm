bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,fopen,fclose,fread           ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll  
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll ; exit is a function that ends the calling process. It is         defined in msvcrt.dll
                                    ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    nume_fis db 'dictionar.txt',0
    mod_acces db 'r',0
    nume_fis1 db 'text.txt',0
    mod_acces1 db 'r',0
    descriptor_fis dd -1
    descriptor_fis1 dd  -1
    nr_car_citite dd 0
    nr_car_citite1 dd 0
    len equ 1
    a resb len
    c times 100 db 0
    d times 100 db 0
    len_c db 10
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
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
            
            
            mov bl,[a]
            mov [c+edi],bl 
            inc edi
            
            
            cmp eax, 0
            je cleanup
            
            mov [nr_car_citite],eax
            
            jmp bucla
            
        cleanup:
            push dword [descriptor_fis]
            call [fclose]
            add esp, 4
            
        final:
            ;mov bl,0
            ;dec edi
            ;mov [c+edi],bl
         
            
        
        push dword mod_acces1
        push dword nume_fis1
        call [fopen]
        add esp, 4*2
        
        mov [descriptor_fis1],eax
        
        cmp eax,0
        je final1
        
        
        mov edi,0
        bucla1:
            push dword [descriptor_fis1]
            push dword len
            push dword 1
            push dword a
            call [fread]
            add esp, 4*4
            
            mov bl,[a]
            cmp bl,'0'
            jl adauga
            
            cmp bl, '9'
            jg adauga
            
            mov esi, [a]
            sub esi, 48
            mov bx, [c+esi]
            jmp adauga
            
            adauga:
                mov [d+edi],bx
                inc edi
                jmp sfarsit1
                
            sfarsit1:
                cmp eax,0
                je cleanup1
                mov [nr_car_citite1],eax
                
                jmp bucla1
                
        cleanup1:
        push dword [descriptor_fis1]
        call [fclose]
        add esp,4
        
        final1:
            
            
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
