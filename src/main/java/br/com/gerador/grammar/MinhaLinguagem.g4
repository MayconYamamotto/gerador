grammar MinhaLinguagem;

@header {
package br.com.gerador.grammar;
}

program : 'hello' ID;
ID : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip ;
