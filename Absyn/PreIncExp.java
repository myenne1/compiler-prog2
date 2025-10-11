package Absyn;
public class PreIncExp extends Exp{
    public Var var;

    public PreIncExp(int p, Var v) {
        pos = p;
        var = v;
    }
}
