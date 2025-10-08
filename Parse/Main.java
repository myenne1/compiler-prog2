package Parse;

import java.io.PrintWriter;

public class Main {
  public static void main(String argv[]) throws java.io.IOException {

    for (int i = 0; i < argv.length; ++i) {

      String filename = argv[i];

      if (argv.length > 1)

	System.out.println("***Processing: " + filename);

      ErrorMsg.ErrorMsg errorMsg = new ErrorMsg.ErrorMsg(filename);

      java.io.InputStream inp=new java.io.FileInputStream(filename);

      Lexer lexer = new Yylex(inp,errorMsg);

      java_cup.runtime.Symbol tok;

      do {

	 String extra = "";

         tok=lexer.nextToken();

	 switch (tok.sym) {

	 case sym.ID:     extra = "\t$" + tok.value; break;

	 case sym.DECIMAL_LITERAL:    extra = "\t#" + tok.value; break;

	 case sym.STRING_LITERAL: extra = " \"" + tok.value + "\""; break;

	 }

	 System.out.println(symnames[tok.sym] + " " + tok.left + extra);

      } while (tok.sym != sym.EOF);



      inp.close();

    }

  }



static String symnames[] = new String[100]; 
 static {
symnames[sym.AUTO] = "AUTO";
symnames[sym.BREAK] = "BREAK";
symnames[sym.CASE] = "CASE";
symnames[sym.CHAR] = "CHAR";
symnames[sym.CONST] = "CONST";
symnames[sym.CONTINUE] = "CONTINUE";
symnames[sym.DEFAULT] = "DEFAULT";
symnames[sym.DO] = "DO";
symnames[sym.ELSE] = "ELSE";
symnames[sym.ENUM] = "ENUM";
symnames[sym.EXTERN] = "EXTERN";
symnames[sym.FOR] = "FOR";
symnames[sym.GOTO] = "GOTO";
symnames[sym.IF] = "IF";
symnames[sym.INT] = "INT";
symnames[sym.LONG] = "LONG";
symnames[sym.REGISTER] = "REGISTER";
symnames[sym.RETURN] = "RETURN";
symnames[sym.SHORT] = "SHORT";
symnames[sym.SIGNED] = "SIGNED";
symnames[sym.SIZEOF] = "SIZEOF";
symnames[sym.STATIC] = "STATIC";
symnames[sym.STRUCT] = "STRUCT";
symnames[sym.SWITCH] = "SWITCH";
symnames[sym.TYPEDEF] = "TYPEDEF";
symnames[sym.UNION] = "UNION";
symnames[sym.UNSIGNED] = "UNSIGNED";
symnames[sym.VOID] = "VOID";
symnames[sym.VOLATILE] = "VOLATILE";
symnames[sym.WHILE] = "WHILE";
symnames[sym.VAR] = "VAR";
symnames[sym.FUN] = "FUN";
symnames[sym.PLUS] = "PLUS";
symnames[sym.MINUS] = "MINUS";
symnames[sym.TIMES] = "TIMES";
symnames[sym.DIVIDE] = "DIVIDE";
symnames[sym.MOD] = "MODULUS";
symnames[sym.INC] = "INCREMENT";
symnames[sym.DEC] = "DECREMENT";
symnames[sym.ASSIGN] = "ASSIGN";
symnames[sym.EQ] = "EQ";
symnames[sym.NEQ] = "NEQ";
symnames[sym.LT] = "LT";
symnames[sym.LE] = "LE";
symnames[sym.GT] = "GT";
symnames[sym.GE] = "GE";
symnames[sym.ANDAND] = "ANDAND";
symnames[sym.OROR] = "OROR";
symnames[sym.NOT] = "NOT";
symnames[sym.BITAND] = "BITAND";
symnames[sym.BITOR] = "BITOR";
symnames[sym.BITXOR] = "BITXOR";
symnames[sym.BITNOT] = "BITNOT";
symnames[sym.LSHIFT] = "LSHIFT";
symnames[sym.RSHIFT] = "RSHIFT";
symnames[sym.SEMI] = "SEMI";
symnames[sym.COMMA] = "COMMA";
symnames[sym.DOT] = "DOT";
symnames[sym.COLON] = "COLON";
symnames[sym.QUESTION] = "QUESTION";
symnames[sym.LPAREN] = "LPAREN";
symnames[sym.RPAREN] = "RPAREN";
symnames[sym.LBRACK] = "LBRACK";
symnames[sym.RBRACK] = "RBRACK";
symnames[sym.LBRACE] = "LBRACE";
symnames[sym.RBRACE] = "RBRACE";
symnames[sym.ARROW] = "ARROW";
symnames[sym.HASH] = "HASH";
symnames[sym.ID] = "ID";
symnames[sym.DECIMAL_LITERAL] = "DECIMAL_LITERAL";
symnames[sym.CHAR_LITERAL] = "CHAR_LITERAL";
symnames[sym.STRING_LITERAL] = "STRING_LITERAL";
}}
