package Absyn;
import Symbol.Symbol;
//while and do_while 
public class WhileStmt extends Stmt {
	public boolean do_while = false;
	public Exp test;
	public Stmt body;
	public WhileStmt(int p, Exp t, Stmt b) {pos=p; test=t; body=b;}
}
