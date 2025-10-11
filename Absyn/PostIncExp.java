package Absyn;
public class PostIncExp extends Exp{
    public Var var;

    public PostIncExp(int p, Var v) {
        pos = p;
        var = v;
    }
}

