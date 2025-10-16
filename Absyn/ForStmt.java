package Absyn;

public class ForStmt extends Exp {
    public Exp initDec;
    public Exp initExp;
    public Exp cond;
    public Exp incr;
    public Exp body;

    public ForStmt(int p, Exp d, Exp c, Exp i, Exp b) {
        pos = p; initDec = d; initExp = null; cond = c; incr = i; body = b;
    }

}