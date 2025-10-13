package Absyn;

public class TernaryExp extends Exp{

    public Exp cond, thenExp, elseExp;

    public TernaryExp(int p, Exp c, Exp t, Exp e) {pos=p; cond=c; thenExp=t; elseExp=e;}
}
