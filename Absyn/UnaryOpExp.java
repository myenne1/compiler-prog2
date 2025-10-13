package Absyn;
public class UnaryOpExp extends Exp{
    public int operator;  
    public Exp exp;
    
    public UnaryOpExp(int p, int o, Exp e) {  
        pos = p;
        operator = o;
        exp = e;
    }
    
    public static final int ADDR=0, DEREF=1, PLUS=2, MINUS=3, BWISENOT=4, NOT=5;
}