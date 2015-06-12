package Absyn;

import java.util.*;

public class ForStmt extends Stmt {
	Assign initial;
	Expr condition;
	Stmt body;
	Assign next;

	public ForStmt(Assign i, Expr c, Assign n, Stmt b) {
		initial = i;
		condition = c;
		next = n;
		body = b;
	}

	public void printAST() {
		printWriter.print("for (");
		initial.printAST();
		printWriter.print("; ");
		condition.printAST();
		printWriter.print("; ");
		next.printAST();
		printWriter.println(")");

		body.printAST();
	}

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("for(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		body.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}

	public ForStmt semanticAnalysis() {
		ForStmt f = new ForStmt(null, null, null, null);
		f.initial = this.initial.semanticAnalysis();

		int condStartLabelNum = labelNum++;
		printWriter.println("LAB LABEL" + condStartLabelNum);
		f.condition = this.condition.semanticAnalysis();
		int condFinishLabelNum = labelNum++;
		printWriter.println("    JMPZ VR(0)@ LABEL" + condFinishLabelNum);

		if (f.condition.getType() != TypeName.INT) {
			raiseError(TYPE_ERR, "Error in for statement : type of condition should be single number type");
		}

		f.body = this.body.semanticAnalysis();
		f.next = this.next.semanticAnalysis();

		printWriter.println("    JMP LABEL" + condStartLabelNum);
		printWriter.println("LAB LABEL" + condFinishLabelNum);
		return f;
	}
}
