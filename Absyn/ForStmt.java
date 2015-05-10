package Absyn;

import java.util.*;

public class ForStmt {
	Assign initial;
	Expr condition;
	Assign next;
	Stmt body;

	public ForStmt(Assign i, Expr c, Assign n, Stmt b) {
		initial = i;
		condition = c;
		next = n;
		body = b;
	}
}
