package Absyn;

public class DotExp extends Exp{
    public String initial;
    public char name; 
    public String exp; 

    public DotExp(int p, String i, char n, String e) {
        pos = p;
        initial = i;
        name = n;
        exp = e;
    }
    
}
