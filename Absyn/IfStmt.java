package Absyn;

import java.util.*;
import Symbol.Symbol;

public class IfStmt extends Stmt {
	public Exp test;
	public Stmt thenclause;
	public Stmt elseclause; /* optional - maybe null */

	public IfStmt(int p, Exp x, Stmt y) {pos=p; test=x; thenclause=y; elseclause=null;}
	public IfStmt(int p, Exp x, Stmt y, Stmt z) {pos=p; test=x; thenclause=y; elseclause=z;}
}
