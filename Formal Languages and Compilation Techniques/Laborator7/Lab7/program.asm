bits 32
global start
extern exit, scanf, printf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
segment data
format_decimal db "%d",0
var1 dd 0
var2 dd 0
var3 dd 0
var4 dd 0
temp1 dd 1
temp2 dd 1
temp3 dd 1
temp4 dd 1
segment code
start:
MOV EAX, 10
MOV [var1], EAX
MOV EAX, 15
MOV [var2], EAX
MOV EAX, 20
MOV [var3], EAX
MOV EAX, 100
MOV [var4], EAX
MOV DWORD EAX, [var4]
SUB DWORD EAX, [var3]
MOV DWORD [temp1], EAX
MOV EAX, [temp1]
MOV [var4], EAX
XOR EDX, EDX
MOV DWORD EAX, [var4]
DIV DWORD [var3]
MOV DWORD [temp2], EAX
MOV EAX, [temp2]
MOV [var3], EAX
MOV DWORD EAX, [var2]
ADD DWORD EAX, [var3]
MOV DWORD [temp3], EAX
MOV EAX, [temp3]
MOV [var2], EAX
XOR EDX, EDX
MOV EAX, [var1]
MUL DWORD [var2]
MOV [temp4], EAX
MOV EAX, [temp4]
MOV [var1], EAX
PUSH DWORD [var1]
PUSH DWORD format_decimal
CALL [printf]
ADD ESP,4*2
PUSH DWORD 0
CALL [exit]
