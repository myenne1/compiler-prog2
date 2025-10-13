package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}       


%%

"break" {return tok(sym.BREAK, yytext());}
"char"        { return tok(sym.CHAR, yytext()); }
"const"       { return tok(sym.CONST, yytext()); }
"continue"    { return tok(sym.CONTINUE, yytext()); }
"do"    { return tok(sym.DO, yytext()); }
"else"    { return tok(sym.ELSE, yytext()); }
"extern"    { return tok(sym.EXTERN, yytext()); }
"for"   { return tok(sym.FOR, yytext()); }
"if"    { return tok(sym.IF, yytext()); }
"int"   { return tok(sym.INT, yytext()); }
"register"    { return tok(sym.REGISTER, yytext()); }
"return"    { return tok(sym.RETURN, yytext()); }
"sizeof"    { return tok(sym.SIZEOF, yytext()); }
"static"    { return tok(sym.STATIC, yytext()); }
"void"    { return tok(sym.VOID, yytext()); }
"volatile"    { return tok(sym.VOLATILE, yytext()); }
"while"   { return tok(sym.WHILE, yytext()); }
"var"   { return tok(sym.VAR, yytext()); }
"fun"   { return tok(sym.FUN, yytext()); }


[a-zA-Z_][a-zA-Z0-9_]*   { return tok(sym.ID, yytext()); }

0[xX][0-9a-fA-F]+   { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
0[0-7]+              { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(1),8)); }
[0-9]+                { return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }


\'([^\\\n]|(\\.))\'        { return tok(sym.CHAR_LITERAL, yytext()); }
\"([^\\\n]|(\\.))*\"        { return tok(sym.STRING_LITERAL, yytext()); }

"++"    { return tok(sym.INCREMENT, yytext()); }
"--"    { return tok(sym.DECREMENT, yytext()); }
"=="    { return tok(sym.EQ, yytext()); }
"!="    { return tok(sym.NEQ, yytext()); }
"<="    { return tok(sym.LE, yytext()); }
">="    { return tok(sym.GE, yytext()); }
"&&"    { return tok(sym.ANDAND, yytext()); }
"||"    { return tok(sym.OROR, yytext()); }
"<<"    { return tok(sym.LSHIFT, yytext()); }
">>"    { return tok(sym.RSHIFT, yytext()); }
"+"     { return tok(sym.PLUS, yytext()); }
"-"     { return tok(sym.MINUS, yytext()); }
"*"     { return tok(sym.TIMES, yytext()); }
"/"     { return tok(sym.DIVIDE, yytext()); }
"%"     { return tok(sym.MODULUS, yytext()); }
"="     { return tok(sym.ASSIGN, yytext()); }
"<"     { return tok(sym.LT, yytext()); }
">"     { return tok(sym.GT, yytext()); }
"!"     { return tok(sym.NOT, yytext()); }
"&"     { return tok(sym.BWISEAND, yytext()); }
"|"     { return tok(sym.BWISEOR, yytext()); }
"^"     { return tok(sym.BWISEXOR, yytext()); }
"~"     { return tok(sym.TILDE, yytext()); }  

";"  { return tok(sym.SEMICOLON, null); }
","  { return tok(sym.COMMA, null); }
"."  { return tok(sym.DOT, null); }
":"  { return tok(sym.COLON, null); }
"?"  { return tok(sym.QUESTION, null); }
"("  { return tok(sym.LPAREN, null); }
")"  { return tok(sym.RPAREN, null); }
"["  { return tok(sym.LBRACK, null); }
"]"  { return tok(sym.RBRACK, null); }
"{"  { return tok(sym.LBRACE, null); }
"}"  { return tok(sym.RBRACE, null); }
"->" { return tok(sym.ARROW, null); }

\n {newline(); } 
" " {}
"\t" {}
"\r" {}
"\f" {}

"//".*               { /* skip single-line comments */ }
"/*"([^*]|(\*+[^*/]))*\*+"/"  { /* skip block comments */ }

. { err("Illegal character: " + yytext()); }
