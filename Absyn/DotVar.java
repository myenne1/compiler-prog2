package Absyn;
import Symbol.Symbol;
public class DotVar extends Var {
   public Var var;
   public Symbol field;
   public DotVar(int p, Var v, Symbol f) {pos=p; var=v; field=f;}
}
