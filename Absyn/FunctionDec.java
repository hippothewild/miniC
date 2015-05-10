package Absyn;

import java.util.*;
import Symbol.Symbol;

public class FunctionDec extends Dec {
	public int typ;
	public Symbol name;
	public FieldList params;
	public int result;  /* optional */// return type
	public CompoudStmt body;

	public FunctionDec(int p, Symbol n, FieldList a, int r, CompoundStmt b)
			       {pos=p; name=n; params=a; result=r; body=b;}
}
