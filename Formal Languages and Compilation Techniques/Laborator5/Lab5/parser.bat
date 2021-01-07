win_flex lab5.lxi
win_bison -d lab5.y
gcc lex.yy.c lab5.tab.c -o lab5
