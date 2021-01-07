%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int yylex();

void yyerror(char *s);

extern FILE *yyin;

extern int mylineno;

char DATA_SEGMENT[1000]; // data segment

char CODE_SEGMENT[1000]; // code segment

void addToDataSegment(char *s);

void addToCodeSegment(char *s);

int isConstant(char *s);

void prepareForExpression(char *destination, char *source);

void writeToFile();

int tempnr = 1;

void newTempName(char *s);

%}

%union val{
  	char id[250];
}

%token INCEPUT
%token INTRODU
%token TIPARESTE
%token SFARSIT
%token INTREG
%token GATA
%token ATRIBUIRE

%token PLUS
%token MINUS
%token ORI
%token DIV
%token PV
%token V
%token PD
%token PI

%token <id> ID
%token <id> CONST_INT

%type <id> expresie
%type <id> variable

%%
prog_sursa: INCEPUT content SFARSIT {printf("Programul este corect sintactic!\n"); return 0;}
		;

content: /* empty */
	   | lista_instructiuni
	   | sect_var
	   | content sect_var
	   | content lista_instructiuni
	   ;
	   
sect_var: tip lista_var PV
		;
		
tip: INTREG                           
   ;

variable: ID 
		{
			strcpy($$, $1);
		}
	

declarare: ID {
	char *tmp = (char*)malloc(sizeof(char)*250);
	sprintf(tmp, "%s dd 0\n", $1);
	addToDataSegment(tmp);
	free(tmp);
}

expresie: variable 
		|	CONST_INT{
			strcpy($$,$1);
		}
		| variable PLUS variable
		{
			char *left_operand = (char *)malloc(sizeof(char)*250);
			char *right_operand = (char *)malloc(sizeof(char)*250);
			prepareForExpression(left_operand, $1);
			prepareForExpression(right_operand, $3);
			char *temp = (char *)malloc(sizeof(char)*250);
			newTempName(temp);
			strcpy($$, temp); 
			char *tmp = (char *)malloc(sizeof(char)*250);

			sprintf(tmp, "MOV DWORD EAX, %s\n", left_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "ADD DWORD EAX, %s\n", right_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "MOV DWORD [%s], EAX\n", temp);
			addToCodeSegment(tmp);
	
			free(left_operand);
			free(right_operand);
			free(temp);
			free(tmp);
		}
		| variable MINUS variable
		{
			char *left_operand = (char *)malloc(sizeof(char)*250);
			char *right_operand = (char *)malloc(sizeof(char)*250);
			prepareForExpression(left_operand, $1);
			prepareForExpression(right_operand, $3);
			char *temp = (char *)malloc(sizeof(char)*250);
			newTempName(temp);
			strcpy($$, temp); 
			char *tmp = (char *)malloc(sizeof(char)*250);
	
			sprintf(tmp, "MOV DWORD EAX, %s\n", left_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "SUB DWORD EAX, %s\n", right_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "MOV DWORD [%s], EAX\n", temp);
			addToCodeSegment(tmp);
	
			free(left_operand);
			free(right_operand);
			free(temp);
			free(tmp);
		}
		| variable ORI variable
		{
			char *left_operand = (char *)malloc(sizeof(char)*250);
			char *right_operand = (char *)malloc(sizeof(char)*250);
			prepareForExpression(left_operand, $1);
			prepareForExpression(right_operand, $3);
			char *temp = (char *)malloc(sizeof(char)*250);
			newTempName(temp);
			strcpy($$, temp); 
			char *tmp = (char *)malloc(sizeof(char)*250);
	
			sprintf(tmp, "XOR EDX, EDX\n");
			addToCodeSegment(tmp);

			sprintf(tmp, "MOV EAX, %s\n", left_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "MUL DWORD %s\n", right_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "MOV [%s], EAX\n", temp);
			addToCodeSegment(tmp);
	
			free(left_operand);
			free(right_operand);
			free(temp);
			free(tmp);
		}
		| variable DIV variable
		{
			char *left_operand = (char *)malloc(sizeof(char)*250);
			char *right_operand = (char *)malloc(sizeof(char)*250);
			prepareForExpression(left_operand, $1);
			prepareForExpression(right_operand, $3);
			char *temp = (char *)malloc(sizeof(char)*250);
			newTempName(temp);
			strcpy($$, temp); 
			char *tmp = (char *)malloc(sizeof(char)*250);
	
			sprintf(tmp, "XOR EDX, EDX\n");
			addToCodeSegment(tmp);

			sprintf(tmp, "MOV DWORD EAX, %s\n", left_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "DIV DWORD %s\n", right_operand);
			addToCodeSegment(tmp);
	
			sprintf(tmp, "MOV DWORD [%s], EAX\n", temp);
			addToCodeSegment(tmp);
	
			free(left_operand);
			free(right_operand);
			free(temp);
			free(tmp);
		}

lista_var: instr_atribuire
		 | declarare
		 | lista_var V instr_atribuire
		 | lista_var V declarare
		 ;
		 
lista_instructiuni: instr
				  | lista_instructiuni instr
				  ;

instr:  instr_atribuire PV
		| instr_introdu PV
		| instr_tipareste PV
		;

instr_atribuire: ID ATRIBUIRE expresie 
{
	char *tmp = (char*)malloc(sizeof(char)*250);
	char *left_operand = (char *)malloc(sizeof(char)*250);
	char *right_operand = (char *)malloc(sizeof(char)*250);
	prepareForExpression(left_operand, $1);
	prepareForExpression(right_operand, $3);

	sprintf(tmp, "MOV EAX, %s\n", right_operand);
	addToCodeSegment(tmp);
	
	sprintf(tmp, "MOV %s, EAX\n", left_operand);
	addToCodeSegment(tmp);
	
	free(left_operand);
	free(right_operand);
	free(tmp);
}	

instr_tipareste:	TIPARESTE PD expresie PI
{
	char *tmp = (char *)malloc(sizeof(char)*250);
	char *operand = (char *)malloc(sizeof(char)*250);
	prepareForExpression(operand, $3);
	
	sprintf(tmp, "PUSH DWORD %s\nPUSH DWORD format_decimal\nCALL [printf]\nADD ESP,4*2\n", operand);
	addToCodeSegment(tmp);

	free(operand);
	free(tmp);
}

instr_introdu:	INTRODU PD variable PI 				
{
	char *tmp = (char *)malloc(sizeof(char)*250);
	char *operand = (char *)malloc(sizeof(char)*250);
	char *str = (char *)malloc(sizeof(char)*250);
	strcpy(str,"Introduceti ");
	strcat(str,$3);
	prepareForExpression(operand, $3);
	
	sprintf(tmp, "PUSH DWORD %s\nPUSH DWORD format_decimal\nCALL [scanf]\nADD ESP,4*2\n", $3);
	addToCodeSegment(tmp);

	free(tmp);
}
%%

//display error
void yyerror(char *s)
{
	printf("%s on line %d\n", s, mylineno);
	exit(0);
}

int main(int argc, char** argv)
{
    printf("Generating assembly code...\n");
    memset(DATA_SEGMENT, 0, 1000);
    memset(CODE_SEGMENT, 0 ,1000);
    
    //from file or from command line (line by line)
    if (argc == 2) {
        yyin = fopen(argv[1], "r");
        yyparse();
	printf("Assembly code successfully generated.\n");
	writeToFile();
    }
    return 0;
}

void addToDataSegment(char *s){
    strcat(DATA_SEGMENT, s);
}

void addToCodeSegment(char *s){
    strcat(CODE_SEGMENT, s);
}

int isConstant(char *s)
{
	// check if it is numerical 0
	if (strlen(s) && s[0] == '0')
	{
		return 1;
	}

	return atoi(s);
}

void prepareForExpression(char *destination, char *source)
{
	if (isConstant(source))
	{	
		sprintf(destination, "%s", source);
	}
	else
	{
		sprintf(destination, "[%s]", source);
	}
}

void writeToFile() {
	char *header = (char *) malloc(sizeof(char)*3000);
	char *segmentData = (char *) malloc(sizeof(char)*3000);
	char *segmentCode = (char *) malloc(sizeof(char)*3000);
	
	strcpy(header, "bits 32\n");
	strcat(header, "global start\nextern exit, scanf, printf\nimport exit msvcrt.dll\nimport scanf msvcrt.dll\nimport printf msvcrt.dll\n");
	strcpy(segmentData, "segment data\nformat_decimal db \"%d\",0\n");
	strcat(segmentData, DATA_SEGMENT);
	strcpy(segmentCode, "segment code\nstart:\n");
	strcat(segmentCode ,CODE_SEGMENT);
	strcat(segmentCode, "PUSH DWORD 0\nCALL [exit]\n");

	FILE *f = fopen("program.asm", "w");
	if(f == NULL) {
		perror("Fatal error! -> file program.asm has failed.");
		exit(1);
	}

	fwrite(header, strlen(header), 1, f);
	fwrite(segmentData, strlen(segmentData), 1, f);
	fwrite(segmentCode, strlen(segmentCode), 1, f);

	fclose(f);
	free(header);
	free(segmentData);
	free(segmentCode);
}


void newTempName(char *s) {
	sprintf(s, "temp%d dd 1\n", tempnr);
	addToDataSegment(s);
	sprintf(s, "temp%d", tempnr);
	tempnr++;
}
