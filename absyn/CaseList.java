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

        int prevLabelNum = labelNum++;
        int caseLabelNum = prevLabelNum;
        labelNum += caseList.size();

        for (CaseStmt cs : this.caseList) {
            caseLabelNum++;
            printWriter.println("LAB LABEL" + caseLabelNum);
            c.add(cs.semanticAnalysis());
            if (cs.hasBreak) {
                printWriter.println("    JMP LABEL" + prevLabelNum);
            }
        }

        return c;
    }
}
