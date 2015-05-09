package Absyn;
import Symbol.Symbol;

// for (init; condition; next) statement;
public class ForStmt extends Stmt {
	public AssignStmt init, next;
	public Exp condition;
	public Stmt body;
	
	public ForStmt(int p, AssignStmt ini, Exp cond, AssignStmt n, Stmt s)
	{ pos = p; init = ini; condition =cond; next = n; body = s;}
}

