package Absyn;

import java.util.*;

public class CompoundStmt extends Stmt {
	public DeclList declList;
	public StmtList stmtList;

	public CompoundStmt(DeclList d, StmtList s) {
		declList = d;
		stmtList = s;
	}
}
