package Absyn;

import java.util.*;

public class StmtList extends Absyn {
    ArrayList<Stmt> stmtList;

    public StmtList(Stmt s) {
        stmtList = new ArrayList<Stmt>();
        if (s != null) {
            stmtList.add(s);
        }
    }

    public void add(Stmt s) {
        if (s != null) {
            stmtList.add(s);
        }
    }

    public void printAST() {
        for (Stmt s : stmtList) {
            s.printAST();
        }
    }

    public void printSymTable() {
        for (Stmt s : stmtList) {
            // Don't handle scope depth step in CompoundStmt itself.
            // CompoundStmt used in function, loop, etc... should not be counted as scope depth.
            if (s.getClass().equals(CompoundStmt.class)) {
                // Step one depth;
        		// Retrack scope and symbol list inside the scope based on DFS logic.
        		int tempScopeCount = scopeCount+1;
        		int tempSymbolCount = symbolCount;
        		scopeStack.add("compound(" + tempScopeCount + ")");
        		scopeCount = 0;
        		symbolCount = 0;

        		printSymTableHeader();
        		s.printSymTable();

        		// Recover current scope's scope count and symbol count.
        		symbolCount = tempSymbolCount;
        		scopeCount = tempScopeCount;
        		scopeStack.remove(scopeStack.size() - 1);
            } else {
                s.printSymTable();
            }
        }
    }

    public StmtList semanticAnalysis() {
        StmtList sl = new StmtList(null);
        for (Stmt s : this.stmtList) {
            sl.add(s.semanticAnalysis());
        }
        return sl;
    }
}
