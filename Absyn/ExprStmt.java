package Absyn;

public class ExprStmt extends Stmt{
    public Exp exp;
    public ExprStmt(int p, Exp e) {
        pos = p;
        exp = e;
    }
    
}
