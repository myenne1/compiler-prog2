package Absyn;

import java.util.List;

public class Print {

  java.io.PrintWriter out;

  public Print(java.io.PrintWriter o) { out = o; }

  void indent(int d) {
      for(int i=0; i<d; i++) 
            out.print(' ');
  }

  void say(String s) {
    out.print(s);
  }

  void say(int i) {
    out.print(i);
  }

  void say(boolean b) {
    out.print(b);
  }

  void sayln(String s) {
    out.println(s);
  }

  void prVar(SimpleVar v, int d) {
    say("SimpleVar("); say(v.name.toString()); say(")");
  }
  
  void prVar(DotVar v, int d) {
    sayln("FieldVar(");
    prVar(v.var, d+1); sayln(",");
    indent(d+1); say(v.field.toString()); say(")");
  }

  void prVar(SubscriptVar v, int d) {
    sayln("SubscriptVar(");
    prVar(v.var, d+1); sayln(",");
    prExp(v.index, d+1); say(")");
  }

  /* Print A_var types. Indent d spaces. */
  void prVar(Var v, int d) {
    indent(d);
    if (v instanceof SimpleVar) prVar((SimpleVar) v, d);
    else if (v instanceof DotVar) prVar((DotVar) v, d);
    else if (v instanceof SubscriptVar) prVar((SubscriptVar) v, d);
    else throw new Error("Print.prVar");
  }
  
  void prExp(OpExp e, int d) {
    sayln("OpExp(");
    indent(d+1); 
    switch(e.oper) {
    case OpExp.PLUS: say("PLUS"); break;
    case OpExp.MINUS: say("MINUS"); break;
    case OpExp.MUL: say("MUL"); break;
    case OpExp.DIV: say("DIV"); break;
    case OpExp.EQ: say("EQ"); break;
    case OpExp.NE: say("NE"); break;
    case OpExp.LT: say("LT"); break;
    case OpExp.LE: say("LE"); break;
    case OpExp.GT: say("GT"); break;
    case OpExp.GE: say("GE"); break;
    default:
      throw new Error("Print.prExp.OpExp");
    }
    sayln(",");
    prExp(e.left, d+1); sayln(",");
    prExp(e.right, d+1); say(")");
  }

  void prExp(VarExp e, int d) {
    sayln("VarExp("); prVar(e.var, d+1);
    say(")");
  }

  void prExp(NilExp e, int d) {
    say("NilExp()");
  }

  void prExp(IntExp e, int d) {
    say("IntExp("); say(e.value); say(")");
  }

  void prExp(StrExp e, int d) {
    say("StringExp("); say(e.value); say(")");
  }

  void prExp(CallExp e, int d) {
    say("CallExp("); say(e.func.toString()); sayln(",");
    prExplist(e.args, d+1); say(")");
  }

  void prExp(RecordExp e, int d) {
    say("RecordExp("); say(e.typ.toString());  sayln(",");
    prFieldExpList(e.fields, d+1); say(")");
  }

  void prExp(SeqExp e, int d) {
    sayln("SeqExp(");
    prExp(e.left, d+1); sayln(",");
    prExp(e.right, d+1); sayln(")");
  }

  void prExp(AssignExp e, int d) {
    sayln("AssignExp(");
    prVar(e.left, d+1); sayln(",");
    prExp(e.right, d+1); say(")");
  }
  
  void prExp(IfStmt e, int d) {
    sayln("IfExp(");
    prExp(e.cond, d+1); sayln(",");
    prExp(e.thenPart, d+1);
    if (e.elsePart!=null) { /* else is optional */
      sayln(",");
      prExp(e.elsePart, d+1);
    }
    say(")");
  }

  void prExp(WhileStmt e, int d) {
    sayln("WhileExp(");
    prExp(e.cond, d+1); sayln(",");
    prExp(e.body, d+1); say(")");
  }

  void prExp(ForStmt e, int d) {
    sayln("ForStmt("); 
    indent(d + 1); prExp(e.initExp, d + 1); sayln(",");
    indent(d + 1); prExp(e.cond, d + 1); sayln(",");
    indent(d + 1); prExp(e.incr, d + 1); sayln(",");
    indent(d + 1); prExp(e.body, d + 1); say(")");
  }

  void prExp(BreakStmt e, int d) {
    say("BreakExp()");
  }

  void prExp(LetExp e, int d) {
    say("LetExp("); sayln("");
    prDecList(e.decs, d+1); sayln(",");
    prExp(e.body, d+1); say(")");
  }

  void prExp(ArrayType e, int d) {
    sayln("ArrayType(");
    prExp(e.baseType, d + 1); sayln(",");
    prExp(e.size, d + 1); say(")");
  }

  /* Print Exp class types. Indent d spaces. */
  public void prExp(Exp e, int d) {
    indent(d);
    if (e instanceof OpExp) prExp((OpExp)e, d);
    else if (e instanceof VarExp) prExp((VarExp) e, d);
    else if (e instanceof NilExp) prExp((NilExp) e, d);
    else if (e instanceof IntExp) prExp((IntExp) e, d);
    else if (e instanceof StrExp) prExp((StrExp) e, d);
    else if (e instanceof CallExp) prExp((CallExp) e, d);
    else if (e instanceof RecordExp) prExp((RecordExp) e, d);
    else if (e instanceof SeqExp) prExp((SeqExp) e, d);
    else if (e instanceof AssignExp) prExp((AssignExp) e, d);
    else if (e instanceof IfStmt) prExp((IfStmt) e, d);
    else if (e instanceof WhileStmt) prExp((WhileStmt) e, d);
    else if (e instanceof ForStmt) prExp((ForStmt) e, d);
    else if (e instanceof BreakStmt) prExp((BreakStmt) e, d);
    else if (e instanceof LetExp) prExp((LetExp) e, d);
    else if (e instanceof ArrayAccessExp) prExp((ArrayAccessExp) e, d);
    else throw new Error("Print.prExp");
  }

  void prStmt(CompoundStmt s, int d) {
    sayln("CompoundStmt(");
    indent(d + 1); sayln("Declarations(");
    prDecList(s.decList, d + 2); sayln("),");
    indent(d + 1); sayln("Statements(");
    // Assuming a prStmtList method or similar for statement lists
    // For now, let's just print each statement
    if (s.stmtList != null) {
      for (Stmt stmt : s.stmtList) {
        prStmt(stmt, d + 2); sayln(",");
      }
    }
    indent(d + 1); sayln(")");
    indent(d); say(")");
  }

  void prStmt(Stmt s, int d) {
    indent(d);
    if (s instanceof CompoundStmt) prStmt((CompoundStmt) s, d);
    // Add other Stmt types here as needed
    else throw new Error("Print.prStmt");
  }

  void prDec(FunctionDec d, int i) {
    sayln("FunctionDec(");
    indent(i + 1); sayln(d.name.toString() + ",");
  
    // Params
    indent(i + 1); sayln("Params(");
    for (Param p : d.params) {
      indent(i + 2); say(p.name.toString());
      if (p.type != null) {
        say(": ");
        printType(p.type, i + 2);
      }
      sayln(",");
    }
    indent(i + 1); sayln("),");

    // Return type
    indent(i + 1);
    if (d.returnType != null && d.returnType.type != null) {
      say("ReturnType(");
      printType(d.returnType.type, i + 1);
      sayln("),");
    } else {
      sayln("ReturnType(null),");
    }

    // Body
    indent(i + 1);
    prStmt(d.body, i + 1); sayln("");
    indent(i); say(")");
  }
  

  void prDec(VarDec d, int i) {
    say("VarDec("); say(d.name.toString()); sayln(",");
    if (d.type != null) {
      indent(i + 1); printType(d.type, i + 1); sayln(",");
    }
    prExp(d.init, i + 1); say(")");
  }

  void prDec(TypeDec d, int i) {
    if (d!=null) {
      say("TypeDec("); say(d.name.toString()); sayln(",");
      prTy(d.ty, i+1); 
      if (d.next!=null) {
	sayln(","); indent(i+1); prDec(d.next, i+1);
      }
      say(")");
    }
  }

  void prDec(Dec d, int i) {
    indent(i);
    if (d instanceof FunctionDec) prDec((FunctionDec) d, i);
    else if (d instanceof VarDec) prDec((VarDec) d, i);
    else if (d instanceof TypeDec) prDec((TypeDec) d, i);
    else throw new Error("Print.prDec");
  }

  void prTy(NameTy t, int i) {
    say("NameTy("); say(t.name.toString()); say(")");
  }

  void prTy(RecordTy t, int i) {
    sayln("RecordTy(");
    prFieldlist(t.fields, i+1); say(")");
  }

  void prTy(ArrayTy t, int i) {
    say("ArrayTy("); say(t.typ.toString()); say(")");
  }

  void prTy(Type t, int i) {
    if (t!=null) {
      indent(i);
      if (t instanceof NameTy) prTy((NameTy) t, i);
      else if (t instanceof RecordTy) prTy((RecordTy) t, i);
      else if (t instanceof ArrayTy) prTy((ArrayTy) t, i);
      else throw new Error("Print.prTy");
    }
  }

  void prFieldlist(FieldList f, int d) {
    indent(d);
    say("FieldList("); 
    if (f!=null) {
      sayln("");
      indent(d+1); say(f.name.toString()); sayln(",");
      indent(d+1); say(f.typ.toString()); sayln(",");
      indent(d+1); say(f.escape);
      sayln(",");
      prFieldlist(f.tail, d+1);
    }
    say(")");
  }

  void prExplist(ExpList e, int d) {
    indent(d);
    say("ExpList("); 
    if (e!=null) {
      sayln("");
      prExp(e.head, d+1); 
      if (e.tail != null) {
	sayln(","); prExplist(e.tail, d+1);
      }
    }
    say(")");
  }

  void prDecList(DecList v, int d) {
    indent(d);
    say("DecList("); 
    if (v!=null) {
      sayln("");
      prDec(v.head, d+1); sayln(",");
      prDecList(v.tail, d+1);
    }
    say(")");
  }

  void prDecList(List<Dec> v, int d) {
    indent(d);
    say("DecList(");
    if (v != null) {
      sayln("");
      for (Dec dec : v) {
        prDec(dec, d + 1); sayln(",");
      }
    }
    say(")");
  }

  void prFieldExpList(FieldExpList f, int d) {
    indent(d);
    say("FieldExpList("); 
    if (f!=null) {
      sayln("");
      indent(d+1); say(f.name.toString()); sayln(",");
      prExp(f.init, d+1); sayln(",");
      prFieldExpList(f.tail, d+1);
    }
    say(")");
  }

  void printType(Type t, int d) {
    if (t instanceof NameTy) {
      say("NameTy("); say(((NameTy)t).name.toString()); say(")");
    } else if (t instanceof ArrayTy) {
      say("ArrayTy("); say(((ArrayTy)t).typ.toString()); say(")");
    } else if (t instanceof RecordTy) {
      sayln("RecordTy(");
      prFieldlist(((RecordTy)t).fields, d + 1);
      say(")");
    } else {
      say("UnknownType");
    }
  }
}
