bits 32 
segment data use32 class=data
    format_1 db '%x',0                       
segment code use32 public code
global afisare

afisare:
    push dword ebx 
    push dword format_1
    call [printf]
    add esp, 4*2
    ret 4 
