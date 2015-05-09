package Absyn;
import Symbol.Symbol;

public class AssignStmt extends Stmt {
   public Var var;
   public Exp exp;
   public AssignStmt(int p, Var v, Exp e) {pos=p; var=v; exp=e;}
}
