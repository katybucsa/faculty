bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll  
import printf msvcrt.dll 
                         ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    n dd 0
    format db "%s",0
; our code starts here
segment code use32 class=code 
    start:
        ; ...
        ;Write a program that reads a decimal number from the keyboard. The number is unsigned, the maximum number of digits before and after the decimal point is 4. Add the value of pi (3.1415) to this number and write the result in a file. The name of the file is also read from the keyboard.

        ;Example:
        ;Input number: 23.543
        ;File name: result.txt
        ;Outcome: the file result.txt will contain the string '26.6845'.
        
        push dword n
        push dword format
        call [scanf]
        add esp,4*2
        mov esi,n
        mov ecx,0
        repet:
            lodsb
            add ecx,1
            cmp byte [esi],0
            jne repet
        
        push ecx 
        mov esi,n 
        mov ebx,0
        jecxz final
        repeta:
            lodsb
            cmp al,'.'
            je punct
            cmp al,0
            je bun
            jmp sari
            bun:
                mov byte [esi-1],'0'
                jmp sari
            punct:
                mov edx,1
                jmp intreg
            sari:
                cmp edx,0
                je intreg
            add ebx,1 
            intreg:
                cmp ebx,4
                jne comp
                jmp l 
            comp:
                cmp ecx,1
                jne l
                add ecx,1
        l:
        loop repeta
        final:
            mov esi,n 
            pop ecx 
            add esi,ecx 
            
            std
            lodsb
            sub ecx,1
            add byte[esi],5
            mov dl,0
            cmp byte[esi],'9'
            ja sc1
            jmp a1 
            sc1:
                sub byte[esi],10
                mov dl,1
                
            a1:
            lodsb 
            sub ecx,1
            add byte[esi],1
            add byte[esi],dl
            mov dl,0
            cmp byte[esi],'9'
            ja sc2
            jmp a2 
            sc2:
                sub byte[esi],10
                mov dl,1                
            a2:
            lodsb 
            sub ecx,1
            add byte[esi],4
            add byte[esi],dl
            mov dl,0
            cmp byte[esi],'9'
            ja sc3
            jmp a3 
            sc3:
                sub byte[esi],10
                mov dl,1
            a3:
            lodsb 
            sub ecx,1
            add byte[esi],1
            add byte[esi],dl
            mov dl,0
            cmp byte[esi],'9'
            ja sc4
            jmp a4 
            sc4:
                sub byte[esi],10
                mov dl,1
            a4:            
            lodsb 
            lodsb 
            sub ecx,1
            add byte[esi],3
            add byte[esi],dl
            mov dl,0
            cmp byte[esi],'9'
            ja sc5
            jmp a5
            
            sc5:
                sub byte[esi],10 
                mov dl,1
            a5:
            jecxz fine 
            lodsb 
            sub ecx,1
            add byte[esi],0
            add byte[esi],dl
            mov dl,0
            cmp byte[esi],'9'
            ja sc6
            jmp a6 
            sc6:
                sub byte[esi],10 
                mov dl,1
            a6:
                jecxz fine 
                lodsb 
                sub ecx,1
                add byte[esi],0
                add byte[esi],dl
                mov dl,0
                cmp byte[esi],'9'
                ja sc7
                jmp a7
            sc7:
                sub byte[esi],10 
                mov dl,1
            a7:
                jecxz fine 
                lodsb 
                sub ecx,1
                add byte[esi],0
                add byte[esi],dl
                mov dl,0
                cmp byte[esi],'9'
                ja sc8
                jmp a8
            sc8:
                sub byte[esi],10 
                mov dl,1
            a8:
                jecxz fine 
                lodsb 
                sub ecx,1
                add byte[esi],0
                add byte[esi],dl
                mov dl,1
                
            
        
        fine:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
