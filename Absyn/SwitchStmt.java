package Absyn;

import java.util.*;

public class SwitchStmt {
	Identifier identifier;
	CaseList caseList;

	public SwitchStmt(Identifier i, CaseList c) {
		identifier = i;
		caseList = c;	// Caselist have defaultStmt
	}
}
