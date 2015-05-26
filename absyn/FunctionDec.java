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

	public void printSymTable() {
        // Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		scopeStack.add(name);
		int tempScopeCount = scopeCount;
		int tempSymbolCount = symbolCount;
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		if (params != null) {
			params.printSymTable();
		}
		body.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
    }

	public FunctionDec semanticAnalysis() {
		// Scope-in one step. (function)
		pushSymbolScope(this.name);
		pushFunctionScope(this.type, this.name);

		FunctionDec f = new FunctionDec(this.type, this.name, null, null);
		if (this.params != null) {
			f.params = this.params.semanticAnalysis();
		}
		f.body = this.body.semanticAnalysis();

		// Scope-out one step.
		popSymbolScope();
		popFunctionScope();
		return f;
	}
}
