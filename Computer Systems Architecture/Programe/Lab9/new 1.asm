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
    c db '.','/','.','*'
    len_c equ $-c
    el dd 0
    ap dd 0
    format1 db '%u',0
    format2 db '%c',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov bl,0
        mov esi,c
        cld
        mov ecx,len_c
        jecxz finalp
        repeta:
            lodsb
            mov dl,0
            push ecx
            mov edi, c
            mov ecx,len_c
            jecxz finalp1
            repeta1:
                scasb
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
                            
                    sfarsit1:
                        loop repeta1
                finalp1:
                    pop ecx 
                    loop repeta
        finalp:
            mov [ap],bl
            push dword [el]
            push dword format2
            call [printf]
            add esp,4*2
            
            
            push dword [ap]
            push dword format1
            call [printf]
            add esp,4*2
                        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program