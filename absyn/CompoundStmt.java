package Absyn;

import java.util.*;

public class CompoundStmt extends Stmt {
	public DeclList declList;
	public StmtList stmtList;

	public CompoundStmt(DeclList d, StmtList s) {
		declList = d;
		stmtList = s;
	}

	public void printAST() {
        printWriter.println("{");
		if (declList != null) {
			declList.printAST();
		}
		if (stmtList != null) {
			stmtList.printAST();
		}
		printWriter.println("}");
    }

	public void printSymTable() {
		if (declList != null) {
			declList.printSymTable();
		}
		if (stmtList != null) {
			stmtList.printSymTable();
		}
	}
}
