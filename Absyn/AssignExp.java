package Absyn;

public class AssignExp extends Exp {
    public Var left;
    public int operator;
    public Exp right;

    public static final int ASSIGN = 0;
    public static final int ADD = 1;
    public static final int SUB = 2;
    public static final int MUL = 3;
    public static final int DIV = 4;
    public static final int MOD = 5;
    public static final int LSHIFT = 6;
    public static final int RSHIFT = 7;
    public static final int BWISEAND = 8;
    public static final int BWISEOR = 9;
    public static final int BWISEXOR = 10;

    public AssignExp(int p, Var l, int o, Exp r) {
        pos = p;
        left = l;
        operator = o;
        right = r;
    }
}