package Absyn;

import java.util.*;

public class DefaultStmt {
    StmtList stmtList;
    boolean haveBreak = false;

    public DefaultStmt(StmtList s, boolean h) {
        stmtList = s;
        haveBreak = h;
    }
}
