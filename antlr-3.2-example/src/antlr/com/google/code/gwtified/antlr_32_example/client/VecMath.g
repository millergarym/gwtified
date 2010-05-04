grammar VecMath;

options {output=AST;} // we want to create ASTs

tokens {
    MULT='*';
    SHIFT;    // needed during simplification
    VEC;      // define imaginary VEC for vector literal
}

@lexer::header {  
package com.google.code.gwtified.antlr_32_example.client;
}

@parser::header {  
package com.google.code.gwtified.antlr_32_example.client;
}

prog:   stat+ ;                         // build list of stat trees
stat:   ID '=' expr  -> ^('=' ID expr)  // '=' is operator subtree root
    |   'print' expr -> ^('print' expr) // 'print' is subtree root
    ;

expr:   multExpr ('+'^ multExpr)* ;     // '+' is root node

multExpr
    :   primary (('*'^|'.'^) primary)*  // '*', '.' are roots
    ;

primary
    :   INT
    |   ID
    |   '[' expr (',' expr)* ']' -> ^(VEC expr+)
    |   '(' expr ')'             -> expr
    ;

ID  :   'a'..'z'+ ;
INT :   '0'..'9'+ ;
WS  :   (' '|'\r'|'\n')+ {skip();} ;