bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    sir db 'cojoc fgdd ewr capac erer',0
    len equ $-sir 
    a db 0,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Se da un sir de octeti reprezentand un text (succesiune de cuvinte separate de spatii). Sa se identifice cuvintele de tip palindrom (ale caror oglindiri sunt similare cu cele de plecare): "cojoc", "capac" etc.
        mov esi,sir 
        mov ecx,len
        mov edx,0     
        mov ebx,0
        jecxz final
        repeta:
            lodsb 
            cmp al,' '
            je compara
            stosb
            add edx,1
            
            compara:
                push ecx 
                push edx 
                sub edx,ebx 
                mov eax, edx 
                div word 2 
                mov dx,0
                cwd 
                mov ecx,eax 
                pop edx 
                push edx 
                jecxz fine 
                repet:
                  mov al,[esi-edx]
                  cmp [esi-ebx],al
                  jne fine 
                  sub edx,1
                  add ebx,1
            
                pop ebx 
                loop repet
                fine:
                
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
