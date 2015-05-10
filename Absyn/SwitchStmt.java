package Absyn;

import java.util.*;

public class SwitchStmt extends Stmt {
	Identifier identifier;
	CaseList caseList;

	public SwitchStmt(Identifier i, CaseList c) {
		identifier = i;
		caseList = c;	// Caselist have defaultStmt
	}
}
