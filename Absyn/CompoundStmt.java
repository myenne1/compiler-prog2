package Absyn;

import java.util.List;

public class CompoundStmt extends Stmt{
    public List<Dec> decList;
    public List<Stmt> stmtList;
    public CompoundStmt(int p, List<Dec> d, List<Stmt> s) {
        pos = p;
        decList = d;
        stmtList = s;
    }
}
