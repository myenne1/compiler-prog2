package Absyn;

public class PostDecExp extends Exp{
    public Var var;
    public PostDecExp(int p, Var v) {
        pos = p;
        var = v;
    }
    
}
