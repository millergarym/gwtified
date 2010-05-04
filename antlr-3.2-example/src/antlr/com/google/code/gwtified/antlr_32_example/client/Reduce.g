tree grammar Reduce;
options {
    tokenVocab=VecMath;      // use tokens from VecMath.g
    ASTLabelType=CommonTree; // we're using CommonTree nodes
    output=AST;              // build ASTs from input AST
    filter=true;             // tree pattern matching, rewrited mode
}

@header {  
package com.google.code.gwtified.antlr_32_example.client;
}

/** Rewrite: x+x to be 2*x, 2*x to be x<<1, x<<n<<m to be x<<(n+m) */
bottomup
    :  ^('+' i=INT j=INT {$i.int==$j.int}?) -> ^(MULT["*"] INT["2"] $j)
    |  ^('*' x=INT {$x.int==2}? y=.)        -> ^(SHIFT["<<"] $y INT["1"])
    |  ^(SHIFT ^(SHIFT e=. n=INT) m=INT)
       -> ^(SHIFT["<<"] $e INT[String.valueOf($n.int+$m.int)])
    ;