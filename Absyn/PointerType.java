package Absyn;

public class PointerType extends Exp{
    public Exp baseType;
    public PointerType(int p) {
        pos = p;
    }

    public Exp wrap(Exp base) {
        this.baseType = base;
        return this;
    }
}
