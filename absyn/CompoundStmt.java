package Absyn;

import java.util.*;

public class CompoundStmt extends Stmt {
	public DeclList declList;
	public StmtList stmtList;

	public CompoundStmt(DeclList d, StmtList s) {
		declList = d;
		stmtList = s;
	}

	public void printAST() {
        printWriter.println("{");
		if (declList != null) {
			declList.printAST();
		}
		if (stmtList != null) {
			stmtList.printAST();
		}
		printWriter.println("}");
    }

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("compound(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		if (declList != null) {
			declList.printSymTable();
		}
		stmtList.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}
}
