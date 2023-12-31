/** Gramatica del Lenguaje LPP en ENBNF para ser procesado por ANTLR4 */
grammar LPP;
@header{
     import java.util.Scanner;
     }

@members {
    Scanner io = new Scanner(System.in);
}

program:  registerDeclarations varDeclarations funProcDeclarations principalBlock;

registerDeclarations: registerDeclaration*;

registerDeclaration: REGISTRO ID varDeclarations FIN REGISTRO ;

funProcDeclarations: ( procDeclaration | funcDeclaration )*;

funcDeclaration: FUNCION ID ( '(' parameters ')' )? ':' varType varDeclarations INICIO funcStmts FIN;

procDeclaration: PROCEDIMIENTO ID ( '(' parameters ')' )?  varDeclarations INICIO stmts FIN;

parameters: parameter ( ',' parameter )*;

parameter: VAR? varType ID;

varDeclarations: varDeclaration*;

varDeclaration: varType idList ;

idList: identifier ( ',' identifier )*;
identifier: ID;

varType
    : ENTERO
    | REAL
    | BOOLEANO
    | CARACTER
    | ID
    | CADENA ( '[' TKN_INTEGER ']')?
    | ARREGLO '[' integerList ']' DE varType;

integerList: TKN_INTEGER ( ',' TKN_INTEGER )*;

funcStmts: stmts;

principalBlock: INICIO  stmts FIN  EOF;

stmts: stmt*;

stmt
    : printStmt
    | readStmt
    | assignStmt
    | callStmt
    | ifStmt
    | caseStmt
    | whileStmt
    | forStmt
    | repeatStmt
    | returnStmt;

printStmt: ESCRIBA exprList ;

readStmt: LEA exprList ;

assignStmt: expr TKN_ASSIGN expr;

callStmt
     : LLAMAR NUEVA_LINEA ( '(' exprList? ')' )?
     | LLAMAR funCall ;

ifStmt: SI expr  ENTONCES  stmts (SINO stmts)? FIN SI;

ifNot: SINO  stmts;

caseStmt: CASO expr  caseElement+ ifNotCase? FIN CASO ;

caseElement: listaExprOpcion ':' stmts;

listaExprOpcion: exprOpcion ( ',' exprOpcion ) *;

exprOpcion: rangeExpr| expr;

rangeExpr: expr '->' expr;

ifNotCase: SINO ':'  stmts;

whileStmt: MIENTRAS expr  HAGA stmts FIN MIENTRAS;

forStmt: PARA forAssign  HASTA expr forBlock;
forBlock: HAGA stmts FIN PARA;
forAssign:  ID '<-' expr;
repeatStmt: REPITA  stmts HASTA expr;

returnStmt: RETORNE expr;

exprList: expr ( ',' expr )*;

TKN_MINUS : '-' ;

funCall: ID (TKN_OPENING_PAR exprList? TKN_CLOSING_PAR)? ;
arrayCall: ID (TKN_OPENING_BRA exprList TKN_CLOSING_BRA)+;
MULOP : TKN_TIMES | TKN_DIV | DIV | MOD | TKN_POWER;

COMOP: TKN_NEQ | TKN_LESS | TKN_GREATER | TKN_LEQ | TKN_GEQ | TKN_EQUAL;
BOLOP: OP_Y | OP_O;

expr
    : literal
    | TKN_MINUS expr
    | ID
    | TKN_OPENING_PAR expr TKN_CLOSING_PAR
    | expr TKN_PERIOD ID
    | expr ( MULOP ) expr
    | lEx=expr op=( TKN_PLUS | TKN_MINUS ) rEx=expr
    | expr ( COMOP ) expr
    | expr ( BOLOP ) expr
    | funCall
    | arrayCall;


literal
    : TKN_REAL
    | TKN_INTEGER
    | TKN_STR
    | TKN_CHAR
    | VERDADERO
    | FALSO
    ;


// RESERVED WORDS
INICIO : I N I C I O ;
FIN : F I N ;
ESCRIBA : E S C R I B A ;
LEA : L E A ;
HASTA : H A S T A ;
REPITA : R E P I T A ;
PROCEDIMIENTO : P R O C E D I M I E N T O ;
VAR : V A R ;
FUNCION : F U N C I O N ;
RETORNE : R E T O R N E ;
LLAMAR : L L A M A R ;
SI : S I ;
ENTONCES : E N T O N C E S ;
SINO : S I N O ;
CASO : C A S O ;
MIENTRAS : M I E N T R A S ;
HAGA : H A G A ;
PARA : P A R A ;
DE : D E ;
NUEVA_LINEA : N U E V A '_' L I N E A ;

// TYPES

ENTERO : E N T E R O ;
REAL : R E A L ;
CARACTER : C A R A C T E R ;
BOOLEANO : B O O L E A N O ;
CADENA : C A D E N A ;
REGISTRO : R E G I S T R O ;
ARREGLO : A R R E G L O ;


// Operators
TKN_PLUS : '+' ;

TKN_POWER : '^';
TKN_TIMES : '*' ;
TKN_DIV : '/' ;
MOD : M O D ;
DIV : D I V ;

// Comparison Operators
TKN_EQUAL : '=' ;
TKN_NEQ : '<>' ;
TKN_GREATER : '>' ;
TKN_LESS : '<' ;
TKN_GEQ : '>=' ;
TKN_LEQ : '<=' ;
OP_Y : Y ;
OP_O : O ;


// SOme other tokens

TKN_OPENING_PAR: '(';
TKN_CLOSING_PAR: ')';
TKN_OPENING_BRA: '[';
TKN_CLOSING_BRA: ']';
TKN_COLON: ':';
TKN_PERIOD: '.';
TKN_COMMA: ',';
TKN_ASSIGN: '<-';

// LITERALS
TKN_REAL : DIGIT+ '.' DIGIT* | '.' DIGIT+;
TKN_INTEGER : DIGIT+ ;
fragment
DIGIT : [0-9] ;
TKN_STR : '"' CHARS_STR* '"' ;
fragment
CHARS_STR : ~["] | '\\"' | '\\\\' ;
TKN_CHAR : '\'' CHAR_CHARS '\'' ;
fragment
CHAR_CHARS : ~['] | '\\\'' | '\\\\' ;
VERDADERO : V E R D A D E R O ;
FALSO : F A L S O ;

// LPP is not case sensitive.
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');

ID : [a-zA-Z$_] [a-zA-Z0-9$_]* ;

NL : [\r\n]+ -> skip;
WS : [ \t]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;