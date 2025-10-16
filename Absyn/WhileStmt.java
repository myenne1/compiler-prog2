package Absyn;

public class WhileStmt extends Stmt {
    public Exp cond;
    public Stmt body;
    public WhileStmt(int p, Exp c, Stmt b) {
        pos = p;
        cond = c;
        body = b;
    }    
}
