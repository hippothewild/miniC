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
}
