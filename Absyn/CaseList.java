package Absyn;

import java.util.*;

public class CaseList {
    ArrayList<CaseStmt> caseList;
    DefaultStmt defaultStmt = null;

    public CaseList() {
		caseList = new ArrayList<CaseStmt>();
	}

	public CaseList(CaseStmt c) {
		caseList = new ArrayList<CaseStmt>();
        caseList.add(c);
	}

    public CaseList(CaseStmt c, DefaultStmt d) {
		caseList = new ArrayList<CaseStmt>();
        caseList.add(c);
        defaultStmt = d;
	}

	public void add(CaseStmt c) {
		caseList.add(c);
	}
}
