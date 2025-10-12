package Absyn;

import java.util.List;
import Symbol.Symbol;

public class Param {
    public int pos;
    public List<String> bitfields;
    public Type type;
    public Symbol name;
    public Param(int p, List<String> b, Type t, Symbol n) {
        pos = p;
        bitfields = b;
        type = t;
        name = n;
    }
}
