package Absyn;

public class ArrayType extends Type{
    public Type baseType;
    public Exp size;

    public ArrayType(int p, Exp s) {
        pos = p;
        size = s;
    }

    public Type wrap(Type base) {
        this.baseType = base;
        return this;
    }
    
}
