tree grammar Simplify;

options {
    tokenVocab=VecMath;      // use tokens from VecMath.g
    ASTLabelType=CommonTree; // we're using CommonTree nodes
    output=AST;              // build ASTs from input AST
    filter=true;             // tree pattern matching, rewrited mode
}

@header {  
package com.google.code.gwtified.antlr_32_example.client;
}

topdown
    :   ^('*' INT ^(VEC (e+=.)+)) -> ^(VEC ^('*' INT $e)+)
    ;

bottomup
    :  ^('*' a=. b=INT {$b.int==0}?) -> $b // x*0 -> 0
    ;