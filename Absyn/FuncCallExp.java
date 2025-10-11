package Absyn;
import java.util.List;
public class FuncCallExp extends Exp{
    public Var func;
    public List<Exp> args;
    public FuncCallExp(int p, Var f, List<Exp> a) {
        pos = p;
        func = f;
        args = a;
    }
}
