package Absyn;

import java.util.*;

public class WhileStmt extends Stmt {
	Expr expr;
	Stmt stmt;
	boolean isDoWhile = false;

	public WhileStmt(Expr e, Stmt s, boolean d) {
		expr = e;
		stmt = s;
		isDoWhile = d;
	}

	public void printAST() {
		if (isDoWhile) {
			printWriter.print("do ");
			stmt.printAST();
			printWriter.print(" while(");
			expr.printAST();
			printWriter.println(");");
		} else {
			printWriter.print("while(");
			expr.printAST();
			printWriter.print(") ");
			stmt.printAST();
		}
	}
}
