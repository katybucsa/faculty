bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                            ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    s dd 12345678h,5bcfh,45327h,1798dfeh,0f112233h
    len equ $-s 
    trei db 3
    format db '%xh',32,0
    sir times len db 0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;Consider a string of double words. Obtain the ascending ordered sequence of bytes of rank multiple of 3 of the given string. 

        ;Example: let the double word string be
        ;s dd 12345678h,5bcfh,45327h,1798dfeh,0f112233h
        ;This string will be represented in memory as:
        ;| 78h| 56h| 34h| 12h| 0cfh| 5bh| 00h| 00h| 27h| 53h| 04h| 00h| 0feh| 8dh| 79h| 01h| 33h| 22h| 11h| 0fh|
        ;The sequence of bytes of rank multiple of 3 of the string s is obtained by concatenating the bytes from ;positions 0,3,6,9,..:
        ;| 78h| 12h| 00h| 53h| 0feh| 01h| 11h|
        ;This sequence must be in ascending order.
        
        mov esi,s 
        mov edi,sir 
        mov ecx,len 
        mov bl,0
        mov edx,0
        jecxz final
        repet:
            lodsb
            mov al,bl 
            mov ah,0
            div byte[trei]
            add bl,1
            cmp ah,0
            jne fine
            add edx,1
            dec esi 
            movsb 
        fine:    
        loop repet    
        final:
            mov ecx,edx 
            sub ecx,1
            mov ebx,1
            mov esi,sir 
            jecxz fin 
            parcurge:
                lodsb 
                cmp al,[esi]
                ja schimba
                jmp f 
                schimba:
                    mov bl,[esi]
                    mov [esi],al 
                    mov [esi-1],bl 
                    mov ebx,0
            f:
            loop parcurge 
            fin:
                cmp ebx,0 
                je final 
                
            mov ecx,edx
            mov esi,sir
            jecxz fi
            afiseaza:
                mov eax,0
                push ecx
                lodsb
                push eax
                push dword format
                call [printf]
                add esp,4*2 
                pop ecx 
            loop afiseaza
            fi:
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
