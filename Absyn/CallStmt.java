package Absyn;
import Symbol.Symbol;

public class CallStmt extends Stmt {
	public Symbol func;
	public ExpList args;
	public CallStmt(int p, Symbol f, ExpList a) {pos=p; func=f; args=a;}
}
