package Absyn;

public class CharExp extends Exp{
    public char value;
    public CharExp(int p, char v) {
        pos = p;
        value = v;
    }
    
}
