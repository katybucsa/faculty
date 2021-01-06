bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a dd 58678898
    b dd 0
    zece dd 10
    format db "%x",10,13,0
    format1 db "%u",10,13,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un numar a reprezentat pe 32 biti fara semn. Se cere sa se afiseze reprezentarea in baza 16 a lui a, precum si rezultatul permutarilor circulare ale cifrelor sale.
        push dword [a]
        push dword format
        call [printf]
        add esp,8
        
        mov eax,[a]
        mov edx,0
        mov ebx,0
        repeta:
            div dword[zece]
            add ebx,1
            cmp eax, 0
            jz final
            mov edx,0
            jmp repeta
        final:
            sub ebx,1
            mov ecx,ebx
            mov [b],ebx 
            push ebx
            jecxz final1
            mov eax,1
            repeta1:
                mul dword [zece]
               loop repeta1
            final1:
                pop ebx 
                mov ecx,ebx 
                mov ebx,eax
                mov eax,[a]
                mov edx,0
                jecxz final2
                repeta2:
                    div dword [zece]
                    push eax 
                    mov eax,ebx 
                    mul edx
                    pop edx 
                    add eax,edx 
                    
                    push eax 
                    
                    mov edx,0
                    loop repeta2
                final2:
                        mov ecx,[b]
                        jecxz final3
                        repeta3:
                            pop eax
                            push ecx
                            push eax 
                            push dword format1
                            call [printf]
                            add esp, 4*2
                            pop ecx 
                        loop repeta3 
                        final3:
                
                
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
