package Absyn;
import Symbol.Symbol;
public class NameTy extends Type {
   public Symbol name;
   public NameTy(int p, Symbol n) {pos=p; name=n;}
}
