package Absyn;

public class ArrayType extends Type{
    public Exp baseType;
    public Exp size;

    public ArrayType(int p, Exp s) {
        pos = p;
        size = s;
    }

    public Type wrap(Exp base) {
        this.baseType = base;
        return this;
    }
    
}
