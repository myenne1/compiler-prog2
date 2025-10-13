package Absyn;

import Absyn.Exp;   // add this

public class DoWhileStmt extends Stmt {
    public Stmt stmt;
    public Exp  exp;

    public DoWhileStmt(int p, Stmt s, Exp e) {
        pos  = p;
        stmt = s;
        exp  = e;
    }
}
