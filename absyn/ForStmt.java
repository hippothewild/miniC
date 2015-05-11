package Absyn;

import java.util.*;

public class ForStmt extends Stmt {
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

	public void printAST() {
		printWriter.print("for (");
		initial.printAST();
		printWriter.print("; ");
		condition.printAST();
		printWriter.print("; ");
		next.printAST();
		printWriter.println(")");

		body.printAST();
	}

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("for(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		body.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}
}
