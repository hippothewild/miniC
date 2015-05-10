package Absyn;

import java.util.*;

public class IfStmt extends Stmt {
	Expr condition;
	Stmt thenStmt;
	Stmt elseStmt;

	public IfStmt(Expr c, Stmt t) {
		condition = c;
		thenStmt = t;
		elseStmt = null;
	}

	public IfStmt(Expr c, Stmt t, Stmt e) {
		condition = c;
		thenStmt = t;
		elseStmt = e;
	}
}
