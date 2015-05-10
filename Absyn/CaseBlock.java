package Absyn;

import java.util.*;

public class CaseBlock {
    CaseList caseList;
    DefaultStmt defaultStmt = null;

	public CaseBlock(CaseList c) {
		caseList = c;
	}

    public CaseBlock(CaseList c, DefaultStmt d) {
		caseList = c;
        defaultStmt = d;
	}
}
