package Absyn;
public class SeqExp extends Exp {
    public Exp left, right;
    public SeqExp(int p, Exp l, Exp r) {pos=p; left=l; right=r;}
}
