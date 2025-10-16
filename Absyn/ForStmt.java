package Absyn;

public class ForStmt extends Stmt {
    public Dec initDec;
    public Exp initExp;
    public Exp cond;
    public Exp incr;
    public Stmt body;

    public ForStmt(int p, Dec d, Exp c, Exp i, Stmt b) {
        pos = p; initDec = d; initExp = null; cond = c; incr = i; body = b;
    }

    public ForStmt(int p, Exp e, Exp c, Exp i, Stmt b) {
        pos = p; initDec = null; initExp = e; cond = c; incr = i; body = b;
    }
}