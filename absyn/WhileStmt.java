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

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		if (isDoWhile) {
			scopeStack.add("do_while(" + tempScopeCount + ")");
		} else {
			scopeStack.add("while(" + tempScopeCount + ")");
		}
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		stmt.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}

	public WhileStmt semanticAnalysis() {
		WhileStmt w = new WhileStmt(null, null, this.isDoWhile);
		w.expr = this.expr.semanticAnalysis();
		if (w.expr.getType() != TypeName.INT && w.expr.getType() != TypeName.FLOAT) {
			raiseError(TYPE_ERR, "Error in for statement : type of condition should be single number type");
		}
		w.stmt = this.stmt.semanticAnalysis();
		return w;
	}
}
