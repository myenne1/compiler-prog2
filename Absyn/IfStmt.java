package Absyn;

public class IfStmt extends Exp{
    public Exp cond;
    public Exp thenPart;
    public Exp elsePart;
    public IfStmt(int p, Exp c, Exp t, Exp e) {
        pos = p;
        cond = c;
        thenPart = t;
        elsePart = e;
    }
    
}
