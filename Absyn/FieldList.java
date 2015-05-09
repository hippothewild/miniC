package Absyn;
import Symbol.Symbol;
public class FieldList extends Absyn {
   public Var name;
   // public Symbol typ; //type is in the symbol table
   public FieldList tail;
   public boolean escape = true;
   public FieldList(int p, Var n, /* Symbol t,*/ FieldList x) {pos=p; name=n; tail=x;}
}
