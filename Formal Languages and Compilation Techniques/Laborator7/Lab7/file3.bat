win_flex lab7.lxi
win_bison -d lab7.y
gcc lex.yy.c lab7.tab.c -o lab7
lab7.exe c.txt
nasm.exe -fobj program.asm
ALINK.EXE program.obj -oPE -subsys console -entry start
program.exe