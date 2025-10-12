package Absyn;

public class ForStmt extends Stmt{
    public Exp exp1;
    public Exp exp2;
    public Exp exp3;
    public Stmt stmt;
    public ForStmt(int p, Exp e1, Exp e2, Exp e3, Stmt s) {
        pos = p;
        exp1 = e1;
        exp2 = e2;
        exp3  = e3;
        stmt = s;
    }
    
}
