package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 1, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		2, 3, 4, 0, 0, 5, 6, 7,
		8, 9, 10, 11, 12, 13, 14, 15,
		16, 17, 17, 17, 17, 17, 17, 17,
		18, 18, 19, 20, 21, 22, 23, 24,
		0, 25, 25, 25, 25, 25, 25, 26,
		26, 26, 26, 26, 26, 26, 26, 26,
		26, 26, 26, 26, 26, 26, 26, 26,
		27, 26, 26, 28, 29, 30, 31, 26,
		0, 32, 33, 34, 35, 36, 37, 38,
		39, 40, 26, 41, 42, 26, 43, 44,
		26, 26, 45, 46, 47, 48, 49, 50,
		51, 26, 52, 53, 54, 55, 56, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 2, 1, 3, 1,
		1, 1, 4, 1, 5, 1, 6, 7,
		1, 1, 8, 9, 10, 1, 11, 1,
		1, 1, 1, 12, 1, 1, 1, 13,
		1, 1, 1, 1, 14, 15, 1, 1,
		1, 1, 1, 1, 1, 1, 11, 11,
		1, 1, 16, 11, 11, 11, 11, 1,
		11, 11, 11, 11, 11, 11, 11, 11,
		11, 11, 11, 11, 11, 13, 13, 17,
		18, 19, 20, 21, 22, 23, 24, 25,
		26, 27, 28, 16, 29, 30, 31, 32,
		33, 34, 35, 36, 37, 38, 39, 40,
		41, 42, 43, 44, 45, 46, 47, 48,
		49, 50, 51, 52, 53, 54, 55, 56,
		57, 58, 59, 60, 61, 62, 63, 64,
		65, 66, 67, 68, 69, 70, 71, 72,
		73, 74, 75, 76, 77, 78, 79, 80,
		81, 82, 83 
	};
	private int yy_nxt[][] = unpackFromString(84,57,
"1,2,3,4,70,5,6,74,7,8,9,10,11,12,13,14,15,71:2,16,17,18,19,20,21,22:3,23,77,24,25,22,126,114,72,115,99,22:2,75,22:4,135,136,22:2,100,127,22:2,26,27,28,29,-1:79,30,-1:40,32,-1:61,33,-1:58,34,-1:9,35,-1:43,81,-1:4,36,-1:57,37:2,71,-1:8,83,-1:23,83,-1:26,38,39,-1:56,40,-1:56,41,42,-1:49,22:3,-1:6,22:3,-1:4,22:21,-1:58,48,-1:2,69,-1,69:2,31,69:24,73,69:27,36,-1,36:55,-1:16,37:2,71,-1:54,50:3,-1:6,50,-1:6,50:6,-1:35,71:3,-1:54,22:3,-1:6,22:3,-1:4,22:12,46,22:8,-1:4,69,-1,69:55,76,-1,76:27,79,76:27,-1:16,22:3,-1:6,22:3,-1:4,22:5,47,22:5,82,22:9,-1:11,49,-1:86,43,-1:7,44,-1,45,-1:25,22:3,-1:6,22:3,-1:4,22:13,51,22:7,-1:4,76,-1,76:55,-1:16,22:3,-1:6,22:3,-1:4,22:11,52,22:9,-1:4,81:10,85,81:46,-1:16,22:3,-1:6,22:3,-1:4,22:15,53,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:13,54,22:7,-1:4,81:10,85,81:4,55,81:41,-1:16,22:3,-1:6,22:3,-1:4,22:13,56,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:4,57,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:3,58,22:17,-1:20,22:3,-1:6,22:3,-1:4,22:9,59,22:11,-1:20,22:3,-1:6,22:3,-1:4,22:15,60,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:4,61,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:11,62,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:11,63,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:5,64,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:2,65,22:18,-1:20,22:3,-1:6,22:3,-1:4,22:4,66,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:13,67,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:4,68,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:12,78,22:3,80,22:4,-1:20,22:3,-1:6,22:3,-1:4,84,22:11,103,22:8,-1:20,22:3,-1:6,22:3,-1:4,86,22:20,-1:20,22:3,-1:6,22:3,-1:4,22:14,87,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:8,88,22,138,22:10,-1:20,22:3,-1:6,22:3,-1:4,89,22:20,-1:20,22:3,-1:6,22:3,-1:4,22:14,90,132,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:10,91,22:10,-1:20,22:3,-1:6,22:3,-1:4,22:13,92,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:13,93,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:12,94,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:8,95,22:12,-1:20,22:3,-1:6,22:3,-1:4,22:16,96,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:4,97,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:10,98,22:10,-1:20,22:3,-1:6,22:3,-1:4,22:7,101,22:4,117,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:10,102,22:8,128,22,-1:20,22:3,-1:6,22:3,-1:4,22:4,104,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:11,105,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:8,106,22:12,-1:20,22:3,-1:6,22:3,-1:4,22:4,107,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:16,108,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:4,109,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:15,110,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:11,111,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:15,112,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:8,113,22:12,-1:20,22:3,-1:6,22:3,-1:4,22:13,116,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:7,118,22:13,-1:20,22:3,-1:6,22:3,-1:4,22:15,119,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:6,137,22:8,120,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:20,121,-1:20,22:3,-1:6,22:3,-1:4,122,22:20,-1:20,22:3,-1:6,22:3,-1:4,22:8,123,22:12,-1:20,22:3,-1:6,22:3,-1:4,22:14,124,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:15,125,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:4,129,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:8,130,22:6,131,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:8,133,22:12,-1:20,22:3,-1:6,22:3,-1:4,134,22:20,-1:4");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

	{
	 return tok(sym.EOF, null);
        }
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{ err("Illegal character: " + yytext()); }
					case -2:
						break;
					case 2:
						{newline(); }
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{ return tok(sym.NOT, yytext()); }
					case -5:
						break;
					case 5:
						{ return tok(sym.MODULUS, yytext()); }
					case -6:
						break;
					case 6:
						{ return tok(sym.BWISEAND, yytext()); }
					case -7:
						break;
					case 7:
						{ return tok(sym.LPAREN, null); }
					case -8:
						break;
					case 8:
						{ return tok(sym.RPAREN, null); }
					case -9:
						break;
					case 9:
						{ return tok(sym.TIMES, yytext()); }
					case -10:
						break;
					case 10:
						{ return tok(sym.PLUS, yytext()); }
					case -11:
						break;
					case 11:
						{ return tok(sym.COMMA, null); }
					case -12:
						break;
					case 12:
						{ return tok(sym.MINUS, yytext()); }
					case -13:
						break;
					case 13:
						{ return tok(sym.DOT, null); }
					case -14:
						break;
					case 14:
						{ return tok(sym.DIVIDE, yytext()); }
					case -15:
						break;
					case 15:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -16:
						break;
					case 16:
						{ return tok(sym.COLON, null); }
					case -17:
						break;
					case 17:
						{ return tok(sym.SEMICOLON, null); }
					case -18:
						break;
					case 18:
						{ return tok(sym.LT, yytext()); }
					case -19:
						break;
					case 19:
						{ return tok(sym.ASSIGN, yytext()); }
					case -20:
						break;
					case 20:
						{ return tok(sym.GT, yytext()); }
					case -21:
						break;
					case 21:
						{ return tok(sym.QUESTION, null); }
					case -22:
						break;
					case 22:
						{ return tok(sym.ID, yytext()); }
					case -23:
						break;
					case 23:
						{ return tok(sym.LBRACK, null); }
					case -24:
						break;
					case 24:
						{ return tok(sym.RBRACK, null); }
					case -25:
						break;
					case 25:
						{ return tok(sym.BWISEXOR, yytext()); }
					case -26:
						break;
					case 26:
						{ return tok(sym.LBRACE, null); }
					case -27:
						break;
					case 27:
						{ return tok(sym.BWISEOR, yytext()); }
					case -28:
						break;
					case 28:
						{ return tok(sym.RBRACE, null); }
					case -29:
						break;
					case 29:
						{ return tok(sym.TILDE, yytext()); }
					case -30:
						break;
					case 30:
						{ return tok(sym.NEQ, yytext()); }
					case -31:
						break;
					case 31:
						{ return tok(sym.STRING_LITERAL, yytext()); }
					case -32:
						break;
					case 32:
						{ return tok(sym.ANDAND, yytext()); }
					case -33:
						break;
					case 33:
						{ return tok(sym.INCREMENT, yytext()); }
					case -34:
						break;
					case 34:
						{ return tok(sym.DECREMENT, yytext()); }
					case -35:
						break;
					case 35:
						{ return tok(sym.ARROW, null); }
					case -36:
						break;
					case 36:
						{ /* skip single-line comments */ }
					case -37:
						break;
					case 37:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(1),8)); }
					case -38:
						break;
					case 38:
						{ return tok(sym.LSHIFT, yytext()); }
					case -39:
						break;
					case 39:
						{ return tok(sym.LE, yytext()); }
					case -40:
						break;
					case 40:
						{ return tok(sym.EQ, yytext()); }
					case -41:
						break;
					case 41:
						{ return tok(sym.GE, yytext()); }
					case -42:
						break;
					case 42:
						{ return tok(sym.RSHIFT, yytext()); }
					case -43:
						break;
					case 43:
						{}
					case -44:
						break;
					case 44:
						{}
					case -45:
						break;
					case 45:
						{}
					case -46:
						break;
					case 46:
						{ return tok(sym.DO, yytext()); }
					case -47:
						break;
					case 47:
						{ return tok(sym.IF, yytext()); }
					case -48:
						break;
					case 48:
						{ return tok(sym.OROR, yytext()); }
					case -49:
						break;
					case 49:
						{ return tok(sym.CHAR_LITERAL, yytext()); }
					case -50:
						break;
					case 50:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
					case -51:
						break;
					case 51:
						{ return tok(sym.FOR, yytext()); }
					case -52:
						break;
					case 52:
						{ return tok(sym.FUN, yytext()); }
					case -53:
						break;
					case 53:
						{ return tok(sym.INT, yytext()); }
					case -54:
						break;
					case 54:
						{ return tok(sym.VAR, yytext()); }
					case -55:
						break;
					case 55:
						{ /* skip block comments */ }
					case -56:
						break;
					case 56:
						{ return tok(sym.CHAR, yytext()); }
					case -57:
						break;
					case 57:
						{ return tok(sym.ELSE, yytext()); }
					case -58:
						break;
					case 58:
						{ return tok(sym.VOID, yytext()); }
					case -59:
						break;
					case 59:
						{return tok(sym.BREAK, yytext());}
					case -60:
						break;
					case 60:
						{ return tok(sym.CONST, yytext()); }
					case -61:
						break;
					case 61:
						{ return tok(sym.WHILE, yytext()); }
					case -62:
						break;
					case 62:
						{ return tok(sym.EXTERN, yytext()); }
					case -63:
						break;
					case 63:
						{ return tok(sym.RETURN, yytext()); }
					case -64:
						break;
					case 64:
						{ return tok(sym.SIZEOF, yytext()); }
					case -65:
						break;
					case 65:
						{ return tok(sym.STATIC, yytext()); }
					case -66:
						break;
					case 66:
						{ return tok(sym.CONTINUE, yytext()); }
					case -67:
						break;
					case 67:
						{ return tok(sym.REGISTER, yytext()); }
					case -68:
						break;
					case 68:
						{ return tok(sym.VOLATILE, yytext()); }
					case -69:
						break;
					case 70:
						{ err("Illegal character: " + yytext()); }
					case -70:
						break;
					case 71:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -71:
						break;
					case 72:
						{ return tok(sym.ID, yytext()); }
					case -72:
						break;
					case 74:
						{ err("Illegal character: " + yytext()); }
					case -73:
						break;
					case 75:
						{ return tok(sym.ID, yytext()); }
					case -74:
						break;
					case 77:
						{ err("Illegal character: " + yytext()); }
					case -75:
						break;
					case 78:
						{ return tok(sym.ID, yytext()); }
					case -76:
						break;
					case 80:
						{ return tok(sym.ID, yytext()); }
					case -77:
						break;
					case 82:
						{ return tok(sym.ID, yytext()); }
					case -78:
						break;
					case 84:
						{ return tok(sym.ID, yytext()); }
					case -79:
						break;
					case 86:
						{ return tok(sym.ID, yytext()); }
					case -80:
						break;
					case 87:
						{ return tok(sym.ID, yytext()); }
					case -81:
						break;
					case 88:
						{ return tok(sym.ID, yytext()); }
					case -82:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -83:
						break;
					case 90:
						{ return tok(sym.ID, yytext()); }
					case -84:
						break;
					case 91:
						{ return tok(sym.ID, yytext()); }
					case -85:
						break;
					case 92:
						{ return tok(sym.ID, yytext()); }
					case -86:
						break;
					case 93:
						{ return tok(sym.ID, yytext()); }
					case -87:
						break;
					case 94:
						{ return tok(sym.ID, yytext()); }
					case -88:
						break;
					case 95:
						{ return tok(sym.ID, yytext()); }
					case -89:
						break;
					case 96:
						{ return tok(sym.ID, yytext()); }
					case -90:
						break;
					case 97:
						{ return tok(sym.ID, yytext()); }
					case -91:
						break;
					case 98:
						{ return tok(sym.ID, yytext()); }
					case -92:
						break;
					case 99:
						{ return tok(sym.ID, yytext()); }
					case -93:
						break;
					case 100:
						{ return tok(sym.ID, yytext()); }
					case -94:
						break;
					case 101:
						{ return tok(sym.ID, yytext()); }
					case -95:
						break;
					case 102:
						{ return tok(sym.ID, yytext()); }
					case -96:
						break;
					case 103:
						{ return tok(sym.ID, yytext()); }
					case -97:
						break;
					case 104:
						{ return tok(sym.ID, yytext()); }
					case -98:
						break;
					case 105:
						{ return tok(sym.ID, yytext()); }
					case -99:
						break;
					case 106:
						{ return tok(sym.ID, yytext()); }
					case -100:
						break;
					case 107:
						{ return tok(sym.ID, yytext()); }
					case -101:
						break;
					case 108:
						{ return tok(sym.ID, yytext()); }
					case -102:
						break;
					case 109:
						{ return tok(sym.ID, yytext()); }
					case -103:
						break;
					case 110:
						{ return tok(sym.ID, yytext()); }
					case -104:
						break;
					case 111:
						{ return tok(sym.ID, yytext()); }
					case -105:
						break;
					case 112:
						{ return tok(sym.ID, yytext()); }
					case -106:
						break;
					case 113:
						{ return tok(sym.ID, yytext()); }
					case -107:
						break;
					case 114:
						{ return tok(sym.ID, yytext()); }
					case -108:
						break;
					case 115:
						{ return tok(sym.ID, yytext()); }
					case -109:
						break;
					case 116:
						{ return tok(sym.ID, yytext()); }
					case -110:
						break;
					case 117:
						{ return tok(sym.ID, yytext()); }
					case -111:
						break;
					case 118:
						{ return tok(sym.ID, yytext()); }
					case -112:
						break;
					case 119:
						{ return tok(sym.ID, yytext()); }
					case -113:
						break;
					case 120:
						{ return tok(sym.ID, yytext()); }
					case -114:
						break;
					case 121:
						{ return tok(sym.ID, yytext()); }
					case -115:
						break;
					case 122:
						{ return tok(sym.ID, yytext()); }
					case -116:
						break;
					case 123:
						{ return tok(sym.ID, yytext()); }
					case -117:
						break;
					case 124:
						{ return tok(sym.ID, yytext()); }
					case -118:
						break;
					case 125:
						{ return tok(sym.ID, yytext()); }
					case -119:
						break;
					case 126:
						{ return tok(sym.ID, yytext()); }
					case -120:
						break;
					case 127:
						{ return tok(sym.ID, yytext()); }
					case -121:
						break;
					case 128:
						{ return tok(sym.ID, yytext()); }
					case -122:
						break;
					case 129:
						{ return tok(sym.ID, yytext()); }
					case -123:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -124:
						break;
					case 131:
						{ return tok(sym.ID, yytext()); }
					case -125:
						break;
					case 132:
						{ return tok(sym.ID, yytext()); }
					case -126:
						break;
					case 133:
						{ return tok(sym.ID, yytext()); }
					case -127:
						break;
					case 134:
						{ return tok(sym.ID, yytext()); }
					case -128:
						break;
					case 135:
						{ return tok(sym.ID, yytext()); }
					case -129:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 137:
						{ return tok(sym.ID, yytext()); }
					case -131:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -132:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
