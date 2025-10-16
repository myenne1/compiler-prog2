package Absyn;

import Symbol.Symbol;

public class FieldList {
    public Symbol name;
    public Symbol typ;
    public boolean escape;
    public FieldList tail;
    public FieldList(int p, Symbol n, Symbol t, boolean e, FieldList tl) {pos=p; name=n; typ=t; escape=e; tail=tl;}
}
