package Absyn;

import java.util.*;

public class WhileStmt {
	Expr expr;
	Stmt stmt;
	boolean isDoWhile = false;

	public WhileStmt(Expr e, Stmt s, boolean d) {
		expr = e;
		stmt = s;
		isDoWhile = d;
	}
}