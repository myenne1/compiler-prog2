package Absyn;
import java.util.List;

import Symbol.Symbol;
public class FunctionDec extends Dec {
  public List<String> bitfields;
  public Param returnType;
  public Symbol name;
  public List<Param> params;
  public CompoundStmt body;
  public FunctionDec(int p, List<String> b, Param r, Symbol n, List<Param> ps, CompoundStmt bo) {
    pos = p;
    bitfields = b;
    returnType = r;
    name = n;
    params = ps;
    body = bo;
  }
  
}
