package Absyn;
public class CastExp extends Exp {
    public Type type;
    public Exp exp;
    public CastExp(int p, Type t, Exp e) {pos=p; type=t; exp=e;}
}
