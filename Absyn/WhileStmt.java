package Absyn;

public class WhileStmt extends Exp {
    public Exp cond;
    public Exp body;
    public WhileStmt(int p, Exp c, Exp b) {
        pos = p;
        cond = c;
        body = b;
    }    
}
