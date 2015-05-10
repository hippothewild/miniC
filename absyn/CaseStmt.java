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
}
