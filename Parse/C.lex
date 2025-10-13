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

private String unescapeString(String s) {
    StringBuilder sb = new StringBuilder();
    // s is the full text, e.g., "\"hello\\nworld\""
    // Get the content between the double quotes.
    String content = s.substring(1, s.length() - 1);
    for (int i = 0; i < content.length(); i++) {
        if (content.charAt(i) == '\\' && i + 1 < content.length()) {
            i++; // Consume the backslash
            switch (content.charAt(i)) {
                case 'n': sb.append('\n'); break;
                case 't': sb.append('\t'); break;
                case 'r': sb.append('\r'); break;
                case '"': sb.append('"'); break;
                case '\\': sb.append('\\'); break;
                default: sb.append(content.charAt(i));
            }
        } else {
            sb.append(content.charAt(i));
        }
    }
    return sb.toString();
}

private Character unescapeChar(String s) {
    // s is the full matched text, e.g., "'\\n'" or "'a'"
    // Get the content between the single quotes.
    String content = s.substring(1, s.length() - 1);
    if (content.charAt(0) == '\\') {
        // This is an escape sequence.
        switch (content.charAt(1)) {
            case 'n': return '\n';
            case 't': return '\t';
            case 'r': return '\r';
            case '\\': return '\\';
            case '\'': return '\'';
            default: return content.charAt(1); // For other escapes like \b, \f, etc.
        }
    } else {
        // This is a simple, unescaped character.
        return content.charAt(0);
    }
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


\'([^\\\n]|(\\.))\'      { return tok(sym.CHAR_LITERAL, unescapeChar(yytext())); }
\"([^\\\n]|(\\.))*\"      { return tok(sym.STRING_LITERAL, unescapeString(yytext())); }

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

"+="    { return tok(sym.ADDASSIGN, yytext()); }
"-="    { return tok(sym.SUBASSIGN, yytext()); }
"*="    { return tok(sym.MULASSIGN, yytext()); }
"/="    { return tok(sym.DIVASSIGN, yytext()); }
"%="    { return tok(sym.MODASSIGN, yytext()); }
"<<="   { return tok(sym.LSHIFTASSIGN, yytext()); }
">>="   { return tok(sym.RSHIFTASSIGN, yytext()); }
"&="    { return tok(sym.BWISEANDASSIGN, yytext()); }
"|="    { return tok(sym.BWISEORASSIGN, yytext()); }
"^="    { return tok(sym.BWISEXORASSIGN, yytext()); }

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
