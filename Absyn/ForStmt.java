package Absyn;

import java.util.*;
import Symbol.Symbol;

public class ForStmt {
	public AssignStmt init, next;
	public Exp condition;
	public Stmt body;

	public ForStmt(int p, AssignStmt ini, Exp cond, AssignStmt n, Stmt s)
	{ pos = p; init = ini; condition =cond; next = n; body = s;}
}
