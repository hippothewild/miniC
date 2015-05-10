package Absyn;

import java.util.*;

public class StmtList {
    ArrayList<Stmt> stmtList;

    public StmtList(Stmt s) {
        stmtList = new ArrayList<Stmt>();
        stmtList.add(s);
    }

    public void add(Stmt s) {
        stmtList.add(s);
    }
}
