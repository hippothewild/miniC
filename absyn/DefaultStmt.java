package Absyn;

import java.util.*;

public class DefaultStmt extends Stmt {
    StmtList stmtList;
    boolean hasBreak = false;

    public DefaultStmt(StmtList s, boolean h) {
        stmtList = s;
        hasBreak = h;
    }

    public void printAST() {
        stmtList.printAST();
        if (hasBreak) {
            printWriter.println("break;");
        }
	}

    public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("default(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		stmtList.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}

    public DefaultStmt semanticAnalysis() {
        DefaultStmt d = new DefaultStmt(null, this.hasBreak);
        d.stmtList = this.stmtList.semanticAnalysis();
        return d;
    }
}
