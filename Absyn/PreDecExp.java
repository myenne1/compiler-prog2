package Absyn;

public class PreDecExp extends Exp{
    public Var var;

    public PreDecExp(int p, Var v) {
        pos = p;
        var = v;
    }
    
}
