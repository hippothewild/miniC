package Absyn;

import java.util.*;

public class SwitchStmt extends Stmt {
	Identifier identifier;
	CaseBlock caseBlock;

	public SwitchStmt(Identifier i, CaseBlock c) {
		identifier = i;
		caseBlock = c;	// CaseBlock have {}(CaseStmt)* (DefaultStmt)}
	}

	public void printAST() {
		printWriter.print("switch (");
		identifier.printAST();
		printWriter.println(")");
		printWriter.println("{");
		caseBlock.printAST();
		printWriter.println("}");
	}
}
