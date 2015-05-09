package Absyn;
import Symbol.Symbol;

public class VarDec extends Dec {
   public FieldList id_list;
   public boolean escape = true;
   public int typ;  // inegert or float
   // public Exp init;  // optional - not supported by miniC
   
   public VarDec(int p, FieldList identlist, int ty) {pos=p; id_list = identlist;  typ=ty;}
}
