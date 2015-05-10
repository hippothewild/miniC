package Absyn;

import java.util.*;

public class StmtList {
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
}
