package Example;

import java_cup.runtime.*;
%%
%cup
%%

"-" { return new Symbol(sym.UNOP); }

"(" { return new Symbol(sym.LPAREN); }
")" { return new Symbol(sym.RPAREN); }
"{" { return new Symbol(sym.LBRACKET); }
"}" { return new Symbol(sym.RBRACKET); }
"[" { return new Symbol(sym.LSQBRACKET); }
"]" { return new Symbol(sym.RSQBRACKET); }

"+" { return new Symbol(sym.PLUS); }
"-" { return new Symbol(sym.MINUS); }
"*" { return new Symbol(sym.TIMES); }
"/" { return new Symbol(sym.DIVIDE); }
"=" { return new Symbol(sym.ASSIGN); }

"<" { return new Symbol(sym.LESS); }
">" { return new Symbol(sym.GREATER); }
"<=" { return new Symbol(sym.LESS_EQ); }
">=" { return new Symbol(sym.GREATER_EQ); }
"==" { return new Symbol(sym.EQ); }
"!=" { return new Symbol(sym.NOT_EQ); }

"int" { return new Symbol(sym.TOKEN_INT); }
"float" { return new Symbol(sym.TOKEN_FLOAT); }
"return" { return new Symbol(sym.TOKEN_RETURN); }
"while" { return new Symbol(sym.TOKEN_WHILE); }
"do" { return new Symbol(sym.TOKEN_DO); }
"for" { return new Symbol(sym.TOKEN_FOR); }
"if" { return new Symbol(sym.TOKEN_IF); }
"else" { return new Symbol(sym.TOKEN_ELSE); }
"switch" { return new Symbol(sym.TOKEN_SWITCH); }
"case" { return new Symbol(sym.TOKEN_CASE); }
"break" { return new Symbol(sym.TOKEN_BREAK); }
"default" { return new Symbol(sym.TOKEN_DEFAULT); }

";" { return new Symbol(sym.SEMI); }
":" { return new Symbol(sym.COLON); }
"," { return new Symbol(sym.COMMA); }

[A-Za-z][A-Za-z0-9_]* { return new Symbol(sym.ID, yytext()); }

[0-9]+ { return new Symbol(sym.INT_NUM, new Integer(yytext())); }
[0-9]+\.[0-9]+ { return new Symbol(sym.FLOAT_NUM, new Float(yytext())); }

[ \t\r\n\f] { /* ignore white space and newline. */ }

. { System.err.println("Illegal character in: "+yytext()); System.exit(0); }
