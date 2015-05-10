package Absyn;

import java.util.*;

public class FunctionDec extends Absyn {
	Type type;
	String name;
	ParamList params;
	CompoundStmt body;

	public FunctionDec(Type t, String n, ParamList p, CompoundStmt b) {
		type = t;
		name = n;
		params = p;
		body = b;
	}

	public void printAST() {
		type.printAST();
		printWriter.printf(" %s(", name);
        if (params != null) {
			params.printAST();
		}
		printWriter.println(")");
		body.printAST();
    }
}
