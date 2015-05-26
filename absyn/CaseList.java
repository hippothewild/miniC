package Absyn;

import java.util.*;

public class CaseList extends Absyn {
    ArrayList<CaseStmt> caseList;

    public CaseList(CaseStmt c) {
        caseList = new ArrayList<CaseStmt>();
        if (c != null) {
            caseList.add(c);
        }
    }

    public void add(CaseStmt c) {
        if (c != null) {
            caseList.add(c);
        }
    }

    public void printAST() {
        for (CaseStmt c : caseList) {
            c.printAST();
        }
    }

    public void printSymTable() {
        for (CaseStmt c : caseList) {
            c.printSymTable();
        }
    }

    public CaseList semanticAnalysis() {
        CaseList c = new CaseList(null);
        for (CaseStmt cs : this.caseList) {
            c.add(cs.semanticAnalysis());
        }
        return c;
    }
}
