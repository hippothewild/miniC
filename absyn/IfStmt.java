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

	public void printAST() {
		printWriter.print("if (");
		condition.printAST();
		printWriter.println(")");
		thenStmt.printAST();
		if (elseStmt != null) {
			printWriter.println("else");
			elseStmt.printAST();
		}
	}

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("if(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		thenStmt.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);

		if (elseStmt != null) {
			// Else statement also counts one depth.
			tempScopeCount = scopeCount+1;
			tempSymbolCount = symbolCount;
			scopeStack.add("else(" + tempScopeCount + ")");
			scopeCount = 0;
			symbolCount = 0;

			printSymTableHeader();
			elseStmt.printSymTable();

			// Recover current scope's scope count and symbol count.
			symbolCount = tempSymbolCount;
			scopeCount = tempScopeCount;
			scopeStack.remove(scopeStack.size() - 1);
		}
	}

	public IfStmt semanticAnalysis() {
		IfStmt i = new IfStmt(null, null, null);
		i.condition = this.condition.semanticAnalysis();
		i.thenStmt = this.thenStmt.semanticAnalysis();
		if (this.elseStmt != null) {
			i.elseStmt = this.elseStmt.semanticAnalysis();
		}
		return i;
	}
}
