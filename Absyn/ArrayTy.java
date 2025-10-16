package Absyn;

import Symbol.Symbol;

public class ArrayTy extends Type {
    public Symbol typ;
    public ArrayTy(int p, Symbol t) {pos=p; typ=t;}
}
