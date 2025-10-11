package Absyn;

public class StrExp extends Exp{
    public String value;
    public StrExp(int p, String v) {
        pos = p;
        value = v;
    }
    
}
