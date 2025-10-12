package Absyn;

public class UnaryOpExp extends Exp{
    public String operator;
    public Exp exp;
    
    public UnaryOpExp(int p, String o, Exp e) {
        pos = p;
        operator = o;
        exp = e;
    }
    
}
