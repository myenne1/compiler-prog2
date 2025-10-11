package Absyn;

public class DotExp extends Exp{
    public String initial;
    public char name; //the dot
    public String exp; // the stuff after the dot

    public DotExp(int p, String i, char n, String e) {
        pos = p;
        initial = i;
        name = n;
        exp = e;
    }
    
}
