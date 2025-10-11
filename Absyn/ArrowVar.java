package Absyn;
import Symbol.Symbol;

public class ArrowVar extends Var{
    public Var var;
    public Symbol field;
    public ArrowVar(int p, Var v, Symbol f) {
        pos = p;
        var = v;
        field = f;
    }
    
}
