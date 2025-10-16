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
		51, 26, 26, 52, 53, 54, 55, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 2, 3, 4, 1,
		1, 5, 6, 1, 7, 1, 8, 9,
		1, 1, 10, 11, 12, 1, 13, 1,
		1, 14, 1, 15, 1, 1, 1, 16,
		1, 1, 1, 1, 1, 1, 1, 1,
		1, 17, 1, 18, 19, 1, 1, 1,
		20, 1, 1, 1, 1, 13, 13, 1,
		1, 1, 21, 1, 1, 13, 13, 13,
		13, 1, 13, 13, 13, 13, 13, 13,
		13, 13, 13, 13, 13, 13, 16, 16,
		22, 23, 24, 25, 26, 27, 28, 29,
		30, 31, 32, 33, 21, 34, 35, 36,
		37, 38, 39, 40, 41, 42, 43, 44,
		45, 46, 47, 48, 49, 50, 51, 52,
		53, 54, 55, 56, 57, 58, 59, 60,
		61, 62, 63, 64, 65, 66, 67, 68,
		69, 70, 71, 72, 73, 74, 75, 76,
		77, 78, 79, 80, 81, 82, 83, 84
		
	};
	private int yy_nxt[][] = unpackFromString(85,56,
"1,2,3,4,79,5,6,83,7,8,9,10,11,12,13,14,15,80:2,16,17,18,19,20,21,22:3,23,86,24,25,22,132,121,81,122,107,22:2,84,22:4,140,141,22:2,108,133,22,26,27,28,29,-1:78,30,-1:55,32,-1:39,33,-1:15,34,-1:55,35,-1:44,36,-1:10,37,-1:46,38,-1:8,39,40,-1:42,90,-1:4,41,-1:6,42,-1:49,43:2,80,-1:8,92,-1:23,92,-1:25,44,45,-1:55,46,-1:55,47,48,-1:48,22:3,-1:6,22:3,-1:4,22:20,-1:26,52,-1:55,55,-1:30,56,-1:2,78,-1,78:2,31,78:24,82,78:26,41,-1,41:54,-1:16,43:2,80,-1:59,59,-1:55,60,-1:49,58:3,-1:6,58,-1:6,58:6,-1:34,80:3,-1:53,22:3,-1:6,22:3,-1:4,22:12,53,22:7,-1:4,78,-1,78:54,85,-1,85:27,88,85:26,-1:16,22:3,-1:6,22:3,-1:4,22:5,54,22:5,91,22:8,-1:11,57,-1:85,49,-1:7,50,-1,51,-1:24,22:3,-1:6,22:3,-1:4,22:13,61,22:6,-1:4,85,-1,85:54,-1:16,22:3,-1:6,22:3,-1:4,22:11,62,22:8,-1:4,90:10,94,90:45,-1:16,22:3,-1:6,22:3,-1:4,22:15,63,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:13,64,22:6,-1:4,90:10,94,90:4,65,90:40,-1:16,22:3,-1:6,22:3,-1:4,22:13,66,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:4,67,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:3,68,22:16,-1:20,22:3,-1:6,22:3,-1:4,22:9,69,22:10,-1:20,22:3,-1:6,22:3,-1:4,22:15,70,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:4,71,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:11,72,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:11,73,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:2,74,22:17,-1:20,22:3,-1:6,22:3,-1:4,22:4,75,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:13,76,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:4,77,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:12,87,22:3,89,22:3,-1:20,22:3,-1:6,22:3,-1:4,93,22:11,111,22:7,-1:20,22:3,-1:6,22:3,-1:4,95,22:19,-1:20,22:3,-1:6,22:3,-1:4,22:14,96,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:8,97,22,143,22:9,-1:20,22:3,-1:6,22:3,-1:4,98,22:19,-1:20,22:3,-1:6,22:3,-1:4,22:14,99,137,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:10,100,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:13,101,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:13,102,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:8,103,22:11,-1:20,22:3,-1:6,22:3,-1:4,22:16,104,22:3,-1:20,22:3,-1:6,22:3,-1:4,22:4,105,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:10,106,22:9,-1:20,22:3,-1:6,22:3,-1:4,22:7,109,22:4,124,22:7,-1:20,22:3,-1:6,22:3,-1:4,22:10,110,22:8,134,-1:20,22:3,-1:6,22:3,-1:4,22:4,112,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:11,113,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:8,114,22:11,-1:20,22:3,-1:6,22:3,-1:4,22:4,115,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:16,116,22:3,-1:20,22:3,-1:6,22:3,-1:4,22:15,117,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:11,118,22:8,-1:20,22:3,-1:6,22:3,-1:4,22:15,119,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:8,120,22:11,-1:20,22:3,-1:6,22:3,-1:4,22:13,123,22:6,-1:20,22:3,-1:6,22:3,-1:4,22:7,125,22:12,-1:20,22:3,-1:6,22:3,-1:4,22:15,126,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:6,142,22:8,127,22:4,-1:20,22:3,-1:6,22:3,-1:4,128,22:19,-1:20,22:3,-1:6,22:3,-1:4,22:8,129,22:11,-1:20,22:3,-1:6,22:3,-1:4,22:14,130,22:5,-1:20,22:3,-1:6,22:3,-1:4,22:15,131,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:4,135,22:15,-1:20,22:3,-1:6,22:3,-1:4,22:15,136,22:4,-1:20,22:3,-1:6,22:3,-1:4,22:8,138,22:11,-1:20,22:3,-1:6,22:3,-1:4,139,22:19,-1:4");
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
						{ return tok(sym.STRING_LITERAL, unescapeString(yytext())); }
					case -32:
						break;
					case 32:
						{ return tok(sym.MODASSIGN, yytext()); }
					case -33:
						break;
					case 33:
						{ return tok(sym.ANDAND, yytext()); }
					case -34:
						break;
					case 34:
						{ return tok(sym.BWISEANDASSIGN, yytext()); }
					case -35:
						break;
					case 35:
						{ return tok(sym.MULASSIGN, yytext()); }
					case -36:
						break;
					case 36:
						{ return tok(sym.INCREMENT, yytext()); }
					case -37:
						break;
					case 37:
						{ return tok(sym.ADDASSIGN, yytext()); }
					case -38:
						break;
					case 38:
						{ return tok(sym.DECREMENT, yytext()); }
					case -39:
						break;
					case 39:
						{ return tok(sym.SUBASSIGN, yytext()); }
					case -40:
						break;
					case 40:
						{ return tok(sym.ARROW, null); }
					case -41:
						break;
					case 41:
						{ /* skip single-line comments */ }
					case -42:
						break;
					case 42:
						{ return tok(sym.DIVASSIGN, yytext()); }
					case -43:
						break;
					case 43:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(1),8)); }
					case -44:
						break;
					case 44:
						{ return tok(sym.LSHIFT, yytext()); }
					case -45:
						break;
					case 45:
						{ return tok(sym.LE, yytext()); }
					case -46:
						break;
					case 46:
						{ return tok(sym.EQ, yytext()); }
					case -47:
						break;
					case 47:
						{ return tok(sym.GE, yytext()); }
					case -48:
						break;
					case 48:
						{ return tok(sym.RSHIFT, yytext()); }
					case -49:
						break;
					case 49:
						{}
					case -50:
						break;
					case 50:
						{}
					case -51:
						break;
					case 51:
						{}
					case -52:
						break;
					case 52:
						{ return tok(sym.BWISEXORASSIGN, yytext()); }
					case -53:
						break;
					case 53:
						{ return tok(sym.DO, yytext()); }
					case -54:
						break;
					case 54:
						{ return tok(sym.IF, yytext()); }
					case -55:
						break;
					case 55:
						{ return tok(sym.BWISEORASSIGN, yytext()); }
					case -56:
						break;
					case 56:
						{ return tok(sym.OROR, yytext()); }
					case -57:
						break;
					case 57:
						{ return tok(sym.CHAR_LITERAL, unescapeChar(yytext())); }
					case -58:
						break;
					case 58:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
					case -59:
						break;
					case 59:
						{ return tok(sym.LSHIFTASSIGN, yytext()); }
					case -60:
						break;
					case 60:
						{ return tok(sym.RSHIFTASSIGN, yytext()); }
					case -61:
						break;
					case 61:
						{ return tok(sym.FOR, yytext()); }
					case -62:
						break;
					case 62:
						{ return tok(sym.FUN, yytext()); }
					case -63:
						break;
					case 63:
						{ return tok(sym.INT, yytext()); }
					case -64:
						break;
					case 64:
						{ return tok(sym.VAR, yytext()); }
					case -65:
						break;
					case 65:
						{ /* skip block comments */ }
					case -66:
						break;
					case 66:
						{ return tok(sym.CHAR, yytext()); }
					case -67:
						break;
					case 67:
						{ return tok(sym.ELSE, yytext()); }
					case -68:
						break;
					case 68:
						{ return tok(sym.VOID, yytext()); }
					case -69:
						break;
					case 69:
						{return tok(sym.BREAK, yytext());}
					case -70:
						break;
					case 70:
						{ return tok(sym.CONST, yytext()); }
					case -71:
						break;
					case 71:
						{ return tok(sym.WHILE, yytext()); }
					case -72:
						break;
					case 72:
						{ return tok(sym.EXTERN, yytext()); }
					case -73:
						break;
					case 73:
						{ return tok(sym.RETURN, yytext()); }
					case -74:
						break;
					case 74:
						{ return tok(sym.STATIC, yytext()); }
					case -75:
						break;
					case 75:
						{ return tok(sym.CONTINUE, yytext()); }
					case -76:
						break;
					case 76:
						{ return tok(sym.REGISTER, yytext()); }
					case -77:
						break;
					case 77:
						{ return tok(sym.VOLATILE, yytext()); }
					case -78:
						break;
					case 79:
						{ err("Illegal character: " + yytext()); }
					case -79:
						break;
					case 80:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -80:
						break;
					case 81:
						{ return tok(sym.ID, yytext()); }
					case -81:
						break;
					case 83:
						{ err("Illegal character: " + yytext()); }
					case -82:
						break;
					case 84:
						{ return tok(sym.ID, yytext()); }
					case -83:
						break;
					case 86:
						{ err("Illegal character: " + yytext()); }
					case -84:
						break;
					case 87:
						{ return tok(sym.ID, yytext()); }
					case -85:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -86:
						break;
					case 91:
						{ return tok(sym.ID, yytext()); }
					case -87:
						break;
					case 93:
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
					case 139:
						{ return tok(sym.ID, yytext()); }
					case -133:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -134:
						break;
					case 141:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -136:
						break;
					case 143:
						{ return tok(sym.ID, yytext()); }
					case -137:
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
