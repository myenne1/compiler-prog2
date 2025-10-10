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
		2, 3, 4, 5, 0, 6, 7, 8,
		9, 10, 11, 12, 13, 14, 15, 16,
		17, 18, 18, 18, 18, 18, 18, 18,
		19, 19, 20, 21, 22, 23, 24, 25,
		0, 26, 26, 26, 26, 26, 26, 27,
		27, 27, 27, 27, 27, 27, 27, 27,
		27, 27, 27, 27, 27, 27, 27, 27,
		28, 27, 27, 29, 30, 31, 32, 27,
		0, 33, 34, 35, 36, 37, 38, 39,
		40, 41, 27, 42, 43, 44, 45, 46,
		47, 27, 48, 49, 50, 51, 52, 53,
		54, 55, 56, 57, 58, 59, 60, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 2, 1, 1, 3,
		1, 1, 1, 4, 1, 5, 1, 6,
		7, 1, 1, 8, 9, 10, 1, 11,
		1, 1, 1, 1, 12, 1, 1, 1,
		13, 1, 1, 1, 1, 14, 15, 1,
		1, 1, 1, 1, 1, 1, 1, 11,
		11, 1, 1, 16, 11, 11, 11, 11,
		1, 11, 11, 11, 11, 11, 11, 11,
		11, 11, 11, 11, 11, 11, 11, 11,
		11, 11, 11, 11, 11, 11, 11, 11,
		11, 11, 11, 13, 13, 17, 18, 19,
		20, 21, 22, 23, 24, 25, 26, 27,
		28, 16, 29, 30, 31, 32, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42,
		43, 44, 45, 46, 47, 48, 49, 50,
		51, 52, 53, 54, 55, 56, 57, 58,
		59, 60, 61, 62, 63, 64, 65, 66,
		67, 68, 69, 70, 71, 72, 73, 74,
		75, 76, 77, 78, 79, 80, 81, 82,
		83, 84, 85, 86, 87, 88, 89, 90,
		91, 92, 93, 94, 95, 96, 97, 98,
		99, 100, 101, 102, 103, 104, 105, 106,
		107, 108, 109, 110, 111, 112, 113, 114,
		115, 116, 117, 118, 119, 120, 121, 122,
		123, 124, 125, 126, 127, 128, 129 
	};
	private int yy_nxt[][] = unpackFromString(130,61,
"1,2,3,4,84,5,6,7,88,8,9,10,11,12,13,14,15,16,85:2,17,18,19,20,21,22,23:3,24,91,25,26,154,177,155,86,156,126,157,23,89,23,158,23:4,192,178,198,179,127,180,23:3,27,28,29,30,-1:84,31,-1:44,33,-1:65,34,-1:62,35,-1:9,36,-1:47,95,-1:4,37,-1:61,38:2,85,-1:8,97,-1:25,97,-1:28,39,40,-1:60,41,-1:60,42,43,-1:53,23:3,-1:6,23:3,-1:4,23:24,-1:62,49,-1:2,83,-1,83:2,32,83:25,87,83:30,37,-1,37:59,-1:17,38:2,85,-1:58,51:3,-1:6,51,-1:6,51:6,-1:39,85:3,-1:58,23:3,-1:6,23:3,-1:4,23:4,193,23:8,47,23:10,-1:4,83,-1,83:59,90,-1,90:28,93,90:30,-1:17,23:3,-1:6,23:3,-1:4,23:5,48,23:6,96,23:11,-1:12,50,-1:90,44,-1:9,45,-1,46,-1:27,23:3,-1:6,23:3,-1:4,23:15,52,23:8,-1:4,90,-1,90:59,-1:17,23:3,-1:6,23:3,-1:4,23:12,53,23:11,-1:4,95:11,99,95:49,-1:17,23:3,-1:6,23:3,-1:4,23:17,54,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:15,55,23:8,-1:4,95:11,99,95:4,56,95:44,-1:17,23:3,-1:6,23:3,-1:4,23:13,57,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:4,58,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:15,59,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:4,60,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:11,61,23:12,-1:21,23:3,-1:6,23:3,-1:4,23:13,62,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:6,63,23:17,-1:21,23:3,-1:6,23:3,-1:4,23:3,64,23:20,-1:21,23:3,-1:6,23:3,-1:4,23:9,65,23:14,-1:21,23:3,-1:6,23:3,-1:4,23:17,66,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:17,67,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:12,68,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:4,69,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:12,70,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:12,71,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:3,72,23:20,-1:21,23:3,-1:6,23:3,-1:4,23:5,73,23:18,-1:21,23:3,-1:6,23:3,-1:4,23:2,74,23:21,-1:21,23:3,-1:6,23:3,-1:4,23:17,75,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:7,76,23:16,-1:21,23:3,-1:6,23:3,-1:4,23:17,77,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:5,78,23:18,-1:21,23:3,-1:6,23:3,-1:4,23:4,79,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:15,80,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:3,81,23:20,-1:21,23:3,-1:6,23:3,-1:4,23:4,82,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:13,92,23:4,94,23:5,-1:21,23:3,-1:6,23:3,-1:4,98,23:12,135,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:17,100,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:16,101,23:7,-1:21,23:3,-1:6,23:3,-1:4,102,23:23,-1:21,23:3,-1:6,23:3,-1:4,23:16,103,23:7,-1:21,23:3,-1:6,23:3,-1:4,23:18,104,23:5,-1:21,23:3,-1:6,23:3,-1:4,23:17,105,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:12,106,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:8,107,23,197,23:13,-1:21,23:3,-1:6,23:3,-1:4,108,23:23,-1:21,23:3,-1:6,23:3,-1:4,23:16,109,188,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:15,110,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:13,111,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:10,112,23:13,-1:21,23:3,-1:6,23:3,-1:4,23:15,113,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:15,114,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:4,115,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:13,116,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:8,117,23:15,-1:21,23:3,-1:6,23:3,-1:4,23:2,118,23:21,-1:21,23:3,-1:6,23:3,-1:4,23:2,119,23:21,-1:21,23:3,-1:6,23:3,-1:4,23:10,120,23:13,-1:21,23:3,-1:6,23:3,-1:4,23:4,121,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:18,122,23:5,-1:21,23:3,-1:6,23:3,-1:4,23:4,123,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:4,124,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:10,125,23:13,-1:21,23:3,-1:6,23:3,-1:4,23:18,128,23:5,-1:21,23:3,-1:6,23:3,-1:4,129,23:6,130,23:5,160,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:10,131,23,132,23:8,181,23:2,-1:21,23:3,-1:6,23:3,-1:4,23:13,133,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:13,134,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:4,136,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:12,137,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:13,138,23:10,-1:21,23:3,-1:6,23:3,-1:4,23:8,139,23:7,196,23:7,-1:21,23:3,-1:6,23:3,-1:4,23:8,140,23:15,-1:21,23:3,-1:6,23:3,-1:4,23:4,141,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:18,142,23:5,-1:21,23:3,-1:6,23:3,-1:4,23:12,143,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:4,144,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:17,145,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:18,146,23:5,-1:21,23:3,-1:6,23:3,-1:4,23:17,147,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:18,148,23:5,-1:21,23:3,-1:6,23:3,-1:4,23:3,149,23:20,-1:21,23:3,-1:6,23:3,-1:4,23:12,150,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:17,151,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:12,152,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:8,153,23:15,-1:21,23:3,-1:6,23:3,-1:4,23:15,159,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:7,161,183,23:8,184,23:2,185,23:3,-1:21,23:3,-1:6,23:3,-1:4,23:12,162,23:11,-1:21,23:3,-1:6,23:3,-1:4,23:7,163,23:16,-1:21,23:3,-1:6,23:3,-1:4,23:17,164,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:6,195,23:10,165,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:6,166,23:16,167,-1:21,23:3,-1:6,23:3,-1:4,168,23:14,169,23:8,-1:21,23:3,-1:6,23:3,-1:4,23:8,170,23:15,-1:21,23:3,-1:6,23:3,-1:4,171,23:23,-1:21,23:3,-1:6,23:3,-1:4,23:4,172,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:8,173,23:15,-1:21,23:3,-1:6,23:3,-1:4,23:16,174,23:7,-1:21,23:3,-1:6,23:3,-1:4,23:6,175,23:17,-1:21,23:3,-1:6,23:3,-1:4,23:17,176,23:6,-1:21,23:3,-1:6,23:3,-1:4,23:4,182,23:19,-1:21,23:3,-1:6,23:3,-1:4,23:5,186,23:18,-1:21,23:3,-1:6,23:3,-1:4,23:14,187,23:9,-1:21,23:3,-1:6,23:3,-1:4,23:8,189,23:15,-1:21,23:3,-1:6,23:3,-1:4,23:8,190,23:15,-1:21,23:3,-1:6,23:3,-1:4,191,23:23,-1:21,23:3,-1:6,23:3,-1:4,23:22,194,23,-1:4");
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
						{ return tok(sym.HASH, null); }
					case -6:
						break;
					case 6:
						{ return tok(sym.MODULUS, yytext()); }
					case -7:
						break;
					case 7:
						{ return tok(sym.BWISEAND, yytext()); }
					case -8:
						break;
					case 8:
						{ return tok(sym.LPAREN, null); }
					case -9:
						break;
					case 9:
						{ return tok(sym.RPAREN, null); }
					case -10:
						break;
					case 10:
						{ return tok(sym.TIMES, yytext()); }
					case -11:
						break;
					case 11:
						{ return tok(sym.PLUS, yytext()); }
					case -12:
						break;
					case 12:
						{ return tok(sym.COMMA, null); }
					case -13:
						break;
					case 13:
						{ return tok(sym.MINUS, yytext()); }
					case -14:
						break;
					case 14:
						{ return tok(sym.DOT, null); }
					case -15:
						break;
					case 15:
						{ return tok(sym.DIVIDE, yytext()); }
					case -16:
						break;
					case 16:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -17:
						break;
					case 17:
						{ return tok(sym.COLON, null); }
					case -18:
						break;
					case 18:
						{ return tok(sym.SEMICOLON, null); }
					case -19:
						break;
					case 19:
						{ return tok(sym.LT, yytext()); }
					case -20:
						break;
					case 20:
						{ return tok(sym.ASSIGN, yytext()); }
					case -21:
						break;
					case 21:
						{ return tok(sym.GT, yytext()); }
					case -22:
						break;
					case 22:
						{ return tok(sym.QUESTION, null); }
					case -23:
						break;
					case 23:
						{ return tok(sym.ID, yytext()); }
					case -24:
						break;
					case 24:
						{ return tok(sym.LBRACK, null); }
					case -25:
						break;
					case 25:
						{ return tok(sym.RBRACK, null); }
					case -26:
						break;
					case 26:
						{ return tok(sym.BWISEXOR, yytext()); }
					case -27:
						break;
					case 27:
						{ return tok(sym.LBRACE, null); }
					case -28:
						break;
					case 28:
						{ return tok(sym.BWISEOR, yytext()); }
					case -29:
						break;
					case 29:
						{ return tok(sym.RBRACE, null); }
					case -30:
						break;
					case 30:
						{ return tok(sym.BWISENOT, yytext()); }
					case -31:
						break;
					case 31:
						{ return tok(sym.NEQ, yytext()); }
					case -32:
						break;
					case 32:
						{ return tok(sym.STRING_LITERAL, yytext()); }
					case -33:
						break;
					case 33:
						{ return tok(sym.ANDAND, yytext()); }
					case -34:
						break;
					case 34:
						{ return tok(sym.INCREMENT, yytext()); }
					case -35:
						break;
					case 35:
						{ return tok(sym.DECREMENT, yytext()); }
					case -36:
						break;
					case 36:
						{ return tok(sym.ARROW, null); }
					case -37:
						break;
					case 37:
						{ /* skip single-line comments */ }
					case -38:
						break;
					case 38:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(1),8)); }
					case -39:
						break;
					case 39:
						{ return tok(sym.LSHIFT, yytext()); }
					case -40:
						break;
					case 40:
						{ return tok(sym.LE, yytext()); }
					case -41:
						break;
					case 41:
						{ return tok(sym.EQ, yytext()); }
					case -42:
						break;
					case 42:
						{ return tok(sym.GE, yytext()); }
					case -43:
						break;
					case 43:
						{ return tok(sym.RSHIFT, yytext()); }
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
						{}
					case -47:
						break;
					case 47:
						{ return tok(sym.DO, yytext()); }
					case -48:
						break;
					case 48:
						{ return tok(sym.IF, yytext()); }
					case -49:
						break;
					case 49:
						{ return tok(sym.OROR, yytext()); }
					case -50:
						break;
					case 50:
						{ return tok(sym.CHAR_LITERAL, yytext()); }
					case -51:
						break;
					case 51:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext().substring(2),16)); }
					case -52:
						break;
					case 52:
						{ return tok(sym.FOR, yytext()); }
					case -53:
						break;
					case 53:
						{ return tok(sym.FUN, yytext()); }
					case -54:
						break;
					case 54:
						{ return tok(sym.INT, yytext()); }
					case -55:
						break;
					case 55:
						{ return tok(sym.VAR, yytext()); }
					case -56:
						break;
					case 56:
						{ /* skip block comments */ }
					case -57:
						break;
					case 57:
						{return tok(sym.AUTO, yytext());}
					case -58:
						break;
					case 58:
						{return tok(sym.CASE, yytext());}
					case -59:
						break;
					case 59:
						{ return tok(sym.CHAR, yytext()); }
					case -60:
						break;
					case 60:
						{ return tok(sym.ELSE, yytext()); }
					case -61:
						break;
					case 61:
						{ return tok(sym.ENUM, yytext()); }
					case -62:
						break;
					case 62:
						{ return tok(sym.GOTO, yytext()); }
					case -63:
						break;
					case 63:
						{ return tok(sym.LONG, yytext()); }
					case -64:
						break;
					case 64:
						{ return tok(sym.VOID, yytext()); }
					case -65:
						break;
					case 65:
						{return tok(sym.BREAK, yytext());}
					case -66:
						break;
					case 66:
						{ return tok(sym.CONST, yytext()); }
					case -67:
						break;
					case 67:
						{ return tok(sym.SHORT, yytext()); }
					case -68:
						break;
					case 68:
						{ return tok(sym.UNION, yytext()); }
					case -69:
						break;
					case 69:
						{ return tok(sym.WHILE, yytext()); }
					case -70:
						break;
					case 70:
						{ return tok(sym.EXTERN, yytext()); }
					case -71:
						break;
					case 71:
						{ return tok(sym.RETURN, yytext()); }
					case -72:
						break;
					case 72:
						{ return tok(sym.SIGNED, yytext()); }
					case -73:
						break;
					case 73:
						{ return tok(sym.SIZEOF, yytext()); }
					case -74:
						break;
					case 74:
						{ return tok(sym.STATIC, yytext()); }
					case -75:
						break;
					case 75:
						{ return tok(sym.STRUCT, yytext()); }
					case -76:
						break;
					case 76:
						{ return tok(sym.SWITCH, yytext()); }
					case -77:
						break;
					case 77:
						{ return tok(sym.DEFAULT, yytext()); }
					case -78:
						break;
					case 78:
						{ return tok(sym.TYPEDEF, yytext()); }
					case -79:
						break;
					case 79:
						{ return tok(sym.CONTINUE, yytext()); }
					case -80:
						break;
					case 80:
						{ return tok(sym.REGISTER, yytext()); }
					case -81:
						break;
					case 81:
						{ return tok(sym.UNSIGNED, yytext()); }
					case -82:
						break;
					case 82:
						{ return tok(sym.VOLATILE, yytext()); }
					case -83:
						break;
					case 84:
						{ err("Illegal character: " + yytext()); }
					case -84:
						break;
					case 85:
						{ return tok(sym.DECIMAL_LITERAL, Integer.parseInt(yytext())); }
					case -85:
						break;
					case 86:
						{ return tok(sym.ID, yytext()); }
					case -86:
						break;
					case 88:
						{ err("Illegal character: " + yytext()); }
					case -87:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -88:
						break;
					case 91:
						{ err("Illegal character: " + yytext()); }
					case -89:
						break;
					case 92:
						{ return tok(sym.ID, yytext()); }
					case -90:
						break;
					case 94:
						{ return tok(sym.ID, yytext()); }
					case -91:
						break;
					case 96:
						{ return tok(sym.ID, yytext()); }
					case -92:
						break;
					case 98:
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
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 145:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 147:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 148:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 149:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 151:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 153:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -154:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 166:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 167:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 173:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 174:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 175:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 176:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 177:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 178:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					case 179:
						{ return tok(sym.ID, yytext()); }
					case -173:
						break;
					case 180:
						{ return tok(sym.ID, yytext()); }
					case -174:
						break;
					case 181:
						{ return tok(sym.ID, yytext()); }
					case -175:
						break;
					case 182:
						{ return tok(sym.ID, yytext()); }
					case -176:
						break;
					case 183:
						{ return tok(sym.ID, yytext()); }
					case -177:
						break;
					case 184:
						{ return tok(sym.ID, yytext()); }
					case -178:
						break;
					case 185:
						{ return tok(sym.ID, yytext()); }
					case -179:
						break;
					case 186:
						{ return tok(sym.ID, yytext()); }
					case -180:
						break;
					case 187:
						{ return tok(sym.ID, yytext()); }
					case -181:
						break;
					case 188:
						{ return tok(sym.ID, yytext()); }
					case -182:
						break;
					case 189:
						{ return tok(sym.ID, yytext()); }
					case -183:
						break;
					case 190:
						{ return tok(sym.ID, yytext()); }
					case -184:
						break;
					case 191:
						{ return tok(sym.ID, yytext()); }
					case -185:
						break;
					case 192:
						{ return tok(sym.ID, yytext()); }
					case -186:
						break;
					case 193:
						{ return tok(sym.ID, yytext()); }
					case -187:
						break;
					case 194:
						{ return tok(sym.ID, yytext()); }
					case -188:
						break;
					case 195:
						{ return tok(sym.ID, yytext()); }
					case -189:
						break;
					case 196:
						{ return tok(sym.ID, yytext()); }
					case -190:
						break;
					case 197:
						{ return tok(sym.ID, yytext()); }
					case -191:
						break;
					case 198:
						{ return tok(sym.ID, yytext()); }
					case -192:
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
