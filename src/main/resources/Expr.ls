grammar Expr
;

prog : expr;

@@KEYWORD :
	 K_AND     'and'
	 K_OR      'or'
	 K_TRUE    'true'
	 K_FALSE   'false'
	 K_NULL    'null'
;

expr : expr ('*'|'/') expr
     | expr ('+'|'-') expr
     | '!' expr
     | expr ('='|'!='|'>='|'<='|'>'|'<') expr
     | expr (K_AND|K_OR) expr
     | fun
     | ID ('.' ID)*
     | '-' expr
     | INT
     | '(' expr ')'
     | STRING
     | DATE
     | BOOLEAN
     | NULL
;

BOOLEAN : K_TRUE
		  | K_FALSE
;

paramArr : ID ('.' ID)*
;
NULL : K_NULL
;
	  
fun: ID '(' ( array )?  ')' ;

array: param (',' param)*;

param: ID  ('[' INT ']')?
	   | INT
       | expr;
  

@ID : #STRING ;
@INT : #NUMBER ;
@STRING : '\'' #STRING '\'';
@DATE : '\'' #STRING '\'';
WS  : [ \t\r\n];