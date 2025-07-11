grammar ProjetoDSL;

@header {
package br.com.gerador.grammar;
}

file        : serviceDecl+ ;

serviceDecl : 'service' ID '{' entityDecl+ '}' ;

entityDecl  : 'entity' ID '{' fieldDecl+ '}' ;

fieldDecl   : ID ':' type option? validation? ;

type        : 'string' | 'uuid' | 'integer' ;

option      : '?' ;

validation  : 'min(' INT ',' STRING ')' ;

ID          : [a-zA-Z_][a-zA-Z_0-9]* ;
INT         : [0-9]+ ;
STRING      : '"' (~["\\] | '\\' .)* '"' ;

WS          : [ \t\r\n]+ -> skip ;
