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
		// Clear esp; will be recovered when function call end.
		esp = 1;
		printWriter.println("LAB " + this.name);

		// Check if function name is declared by another function or variable.
		if (getFunctionScope(this.name) != null) {
			raiseError(SEMANTIC_ERR, "Function name " + this.name + " is already declared by another function.");
		} else if (getSymbolFromSymbolTable(this.name) != null) {
			raiseError(SEMANTIC_ERR, "Function name " + this.name + " is already declared by another variable.");
		}

		// Scope-in one step. (function)
		pushSymbolScope(this.name);
		pushFunctionScope(this.type, this.name);

		FunctionDec f = new FunctionDec(this.type, this.name, null, null);
		if (this.params != null) {
			f.params = this.params.semanticAnalysis();
		}
		f.body = this.body.semanticAnalysis();

		printWriter.println("    MOVE FP@ SP");
		printWriter.println("    MOVE MEM(FP@)@ FP");
		printWriter.println("    SUB SP@ 1 SP");
		printWriter.println("    JMP MEM(SP@)@");

		// Scope-out one step.
		popSymbolScope();
		return f;
	}
}
