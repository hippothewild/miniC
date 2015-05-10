package Absyn;

import java.util.*;
import Symbol.Symbol;

public class CompoundStmt extends Stmt {
	public DecList decllist;
	public StmtSeq stmtlist;
	public CompoundStmt(int p, DecList d, StmtSeq s) {pos=p; decllist=d; stmtlist=s;}

}
