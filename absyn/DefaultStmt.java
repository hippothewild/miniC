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
}
