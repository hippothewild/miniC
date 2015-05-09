package Absyn;
import Symbol.Symbol;

// statement sequence
public class StmtSeq extends Stmt {
	public StmtList list;
	public StmtSeq(int p, StmtList l) {pos=p; list=l;}
}
