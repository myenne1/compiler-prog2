package Absyn;

public class IfStmt extends Stmt{
    public Exp cond;
    public Stmt thenPart;
    public Stmt elsePart;
    public IfStmt(int p, Exp c, Stmt t, Stmt e) {
        pos = p;
        cond = c;
        thenPart = t;
        elsePart = e;
    }
    
}
