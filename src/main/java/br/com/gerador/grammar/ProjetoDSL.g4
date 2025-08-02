grammar ProjetoDSL;

@header {
package br.com.gerador.grammar.antlr4;
}

file: packageDecl+;

packageDecl:
	'package' packageName '{' (entityDecl | crudConfig)+ '}';

packageName: ID ('.' ID)*;

entityDecl: 'entity' ID '{' fieldDecl+ '}';

crudConfig: 'crud' '{' crudOptions* '}';

crudOptions:
	'generateRepository' ':' BOOLEAN
	| 'generateService' ':' BOOLEAN
	| 'generateController' ':' BOOLEAN
	| 'generateDto' ':' BOOLEAN
	| 'dddLayers' ':' BOOLEAN;

fieldDecl: ID ':' type option? validation? modifier?;

type:
	'string'
	| 'uuid'
	| 'integer'
	| 'long'
	| 'double'
	| 'boolean'
	| 'date'
	| 'datetime'
	| 'decimal';

option: '?';

modifier: 'transient';

validation:
	'min(' INT ',' STRING ')'
	| 'max(' INT ')'
	| 'notNull'
	| 'notBlank';

BOOLEAN: 'true' | 'false';
ID: [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
STRING: '"' (~["\\] | '\\' .)* '"';

WS: [ \t\r\n]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;