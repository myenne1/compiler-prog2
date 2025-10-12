package Absyn;

public class ReturnStmt extends Stmt{
    public Exp exp;
    public ReturnStmt(int p, Exp e) {
        pos = p;
        exp = e;
    }
}
