package Absyn;

import java.util.*;

public class CaseBlock extends Absyn {
    CaseList caseList;
    DefaultStmt defaultStmt = null;

	public CaseBlock(CaseList c) {
		caseList = c;
	}

    public CaseBlock(CaseList c, DefaultStmt d) {
		caseList = c;
        defaultStmt = d;
	}

    public void printAST() {
		if (caseList != null) {
            caseList.printAST();
        }
        if (defaultStmt != null) {
            printWriter.println("default:");
            defaultStmt.printAST();
        }
	}

    public void printSymTable() {
        if (caseList != null) {
            caseList.printSymTable();
        }
        if (defaultStmt != null) {
            defaultStmt.printSymTable();
        }
	}

    public CaseBlock semanticAnalysis(int previousLabelNum) {
        CaseBlock c = new CaseBlock(null);

        if (this.defaultStmt != null) {
            int defaultStmtLabelNum = previousLabelNum++;
            int caseStmtLabelNum = previousLabelNum;
            labelNum++;
            blockIdx++;
            for (CaseStmt s : this.caseList.caseList) {
                caseStmtLabelNum++;
                printWriter.println("    SUB VR(0)@ " + s.num + " VR(" + blockIdx + ")");
                printWriter.println("    JMPZ VR(" + blockIdx + ")@ LABEL" + caseStmtLabelNum);
            }
            blockIdx--;
            printWriter.println("    JMP LABEL" + defaultStmtLabelNum);
            c.caseList = this.caseList.semanticAnalysis();
            printWriter.println("LAB LABEL" + defaultStmtLabelNum);
            c.defaultStmt = this.defaultStmt.semanticAnalysis();
            printWriter.println("LAB LABEL" + previousLabelNum);
        } else {
            blockIdx++;
            int caseStmtLabelNum = previousLabelNum;
            for (CaseStmt s : this.caseList.caseList) {
                caseStmtLabelNum++;
                printWriter.println("    SUB VR(0)@ " + s.num + " VR(" + blockIdx + ")");
                printWriter.println("    JMPZ VR(" + blockIdx + ")@ LABEL" + caseStmtLabelNum);
            }
            blockIdx--;
            printWriter.println("    JMP LABEL" + previousLabelNum);
            c.caseList = this.caseList.semanticAnalysis();
            c.defaultStmt = null;
            printWriter.println("LAB LABEL" + previousLabelNum);
        }

        labelNum++;
        return c;
    }
}
