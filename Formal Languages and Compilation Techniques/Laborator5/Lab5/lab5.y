%{
#include <stdio.h>
#include <stdlib.h>
#define YYDEBUG 1
#define YYERRSTATUS 0

double stiva[20];
int sp;

void push(double x)
{ 
stiva[sp++]=x; 
}

double pop()
{ 
return stiva[--sp]; 
}

%}

%union val{
  	int l_val;
	char *p_val;
}

%token INCEPUT
%token REAL
%token INTRODU
%token TIPARESTE
%token SFARSIT
%token INTREG
%token CATTIMP
%token DACA
%token ALTERNATIVA
%token REPETA
%token GATA


%token ID
%token <p_val> CONST_INT
%token <p_val> CONST_REAL
%token <p_val> CONST_CAR
%token <p_val> CONST_SIR

%token ATRIBUIRE
%token LESS
%token GREATER
%token EQUAL
%token DIFF



%left '+' '-'
%left '*' '/'

%type <val> constanta

%%
prog_sursa: INCEPUT content SFARSIT {printf("Programul este corect sintactic!\n");}
		;

content: /* empty */
	   | lista_instructiuni
	   | sect_var
	   | content sect_var
	   | content lista_instructiuni
	   ;
	   
sect_var: tip lista_var ';'
		;
		
tip: INTREG                        
   | REAL                           
   ;
		
constanta:	CONST_INT	{               
			
			push(atoi($1));
				}
		 | CONST_REAL	{
			
			push(atof($1));
		 }
		 ;
		
factor: ID      
	  | constanta        
	  | '(' expresie ')'
	  | tablou
	  ;

declarare: ID
		 | ID '[' constanta ']'
		 ;

expresie: factor
		| expresie '+' expresie
		| expresie '-' expresie
		| expresie '*' expresie
		| expresie '/' expresie
		;

lista_var: instr_atribuire
		 | declarare
		 | lista_var ',' instr_atribuire
		 | lista_var ',' declarare
		 ;
		 
lista_instructiuni: instr
				  | lista_instructiuni instr
				  ;

instr:  instr_atribuire ';'
		| instr_introdu ';'
		| instr_repeta
		| instr_tipareste ';'
		| instr_cattimp
		| instr_daca
		;

instr_atribuire: declarare ATRIBUIRE expresie {printf("ATRIBUIRE\n");}
			   ;

instr_daca:	DACA conditie ':' lista_instructiuni '.' ramura_else	{printf("Instructiunea daca\n");}
		;
		
ramura_else: 
			| ALTERNATIVA ':' lista_instructiuni	'.'								{printf("Ramura else\n");}
		   ;
		
instr_cattimp:	CATTIMP  conditie content GATA  {printf("Instructiunea cattimp!\n");}
			 ;
			
conditie:	factor_logic						
		| expresie op_rel expresie					
		;	

factor_logic:	'(' conditie ')'
		;
		
instr_repeta: REPETA '(' instr_atribuire ';' conditie_repeta ';' instr_atribuire ')' content GATA {printf("Instructiunea repeta\n");}
			;

instr_tipareste:	TIPARESTE '(' lista_elem ')'		{printf(" TIPARESTE\n");}
		;
		
lista_elem:	element
		| lista_elem ',' element
		;
element:	expresie
	   | CONST_SIR
		;
			   
conditie_repeta: expresie op_rel expresie
			    ;
		
op_rel:	  EQUAL										
		| LESS										
		| GREATER									
		| DIFF										
		;
		

instr_introdu:	INTRODU '(' lista_variab ')' 				{printf(" CITIRE\n ");}
		;
lista_variab:	declarare
		| lista_variab ',' declarare
		| tablou
		;

tablou: ID '[' expresie ']'
	  ;
%%

yyerror(char *s)
{
  printf("%s\n", s); 
}

extern FILE *yyin;
extern int yylex_destroy(void);

main(int argc, char **argv)
{

 int cmd;
  char file1[] = "cerc.txt", file2[] = "cmmdc.txt", file3[] = "suma.txt";
  int go = 1;
  while (go == 1) {
	printf("\n0. Exit\n");
	printf("1. Pentru a incarca fisierul cerc.txt\n");
	printf("2. Pentru a incarca fisierul cmmdc.txt\n");
    printf("3. Pentru a incarca fisierul suma.txt\n\n");
	printf("Introduceti comada: ");
	scanf("%d", &cmd);
	int correct = 1;
	if (cmd == 0) {
		return 0;
	}else{
	if (cmd == 1) {
		yyin = fopen(file1,"r");
	}
	else if (cmd == 2) {
		yyin = fopen(file2,"r");
	}
	else if (cmd == 3) {
		yyin = fopen(file3,"r");
	}
	else {
		printf("Comanda gresita\n");
		correct = 0;
	}
	if(correct == 1 && !yyparse()){
		fprintf(stderr,"\tO.K.\n");
	}
	yyrestart();
	}
  }
}