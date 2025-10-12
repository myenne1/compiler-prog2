package Absyn;

public class ArrayAccessExp extends Exp{
    public Var array;
    public Exp index;

    public ArrayAccessExp(int p, Var a, Exp i) {
        pos = p;
        array = a;
        index = i;
    }
    
}
