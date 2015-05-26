package Absyn;

import java.util.*;

public class CaseStmt extends Stmt {
    int num;
    StmtList stmtList;
    boolean hasBreak = false;

    public CaseStmt(int n, StmtList s, boolean h) {
        num = n;
        stmtList = s;
        hasBreak = h;
    }

    public void printAST() {
        printWriter.printf("case %d:\n", num);
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
		scopeStack.add("case(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		stmtList.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}

    public CaseStmt semanticAnalysis() {
        CaseStmt c = new CaseStmt(this.num, null, this.hasBreak);
        c.stmtList = this.stmtList.semanticAnalysis();
        return c;
    }
}
