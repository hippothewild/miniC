package Absyn;

import java.util.*;

public class CaseStmt extends Stmt {
    int num;
    StmtList stmtList;
    boolean haveBreak = false;

    public CaseStmt(int n, StmtList s, boolean h) {
        num = n;
        stmtList = s;
        haveBreak = h;
    }
}
