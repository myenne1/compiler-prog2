package Absyn;

public class PointerType extends Type{
    public Type baseType;
    public PointerType(int p) {
        pos = p;
    }

    public Type wrap(Type base) {
        this.baseType = base;
        return this;
    }
}
