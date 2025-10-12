package Absyn;
import java.util.List;

import Symbol.Symbol;
public class VarDec extends Dec {
   public List<String> bitfields; 
   public Type type;
   public Symbol name;
   public Exp init;
   public VarDec(int p, List<String> b, Type t, Symbol s, Exp i) {
      pos = p;
      bitfields = b;
      type = t;
      name = s;
      init = i;
   }
}
