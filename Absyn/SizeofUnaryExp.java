package Absyn;

public class SizeofUnaryExp extends Exp{
    public Type name;

    public SizeofUnaryExp(int p, Type n) {
        pos = p;
        name = n;
    }
    
}
