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

	public void printSymTable() {
		// Step one depth;
		// Retrack scope and symbol list inside the scope based on DFS logic.
		int tempScopeCount = scopeCount+1;
		int tempSymbolCount = symbolCount;
		scopeStack.add("switch(" + tempScopeCount + ")");
		scopeCount = 0;
		symbolCount = 0;

		printSymTableHeader();
		caseBlock.printSymTable();

		// Recover current scope's scope count and symbol count.
		symbolCount = tempSymbolCount;
		scopeCount = tempScopeCount;
		scopeStack.remove(scopeStack.size() - 1);
	}

	public SwitchStmt semanticAnalysis() {
		Symbol sym = getSymbolFromSymbolTable(this.identifier.name);
        if (sym == null) {
            raiseError(SEMANTIC_ERR, "Variable " + this.identifier.name + " is not declared.");
        }
		sym.tGetValue();
		printWriter.println("    MOVE MEM(VR(0)@)@ VR(0)");

		SwitchStmt ss = new SwitchStmt(null, null);
		int previousLabelNum = labelNum;
		ss.identifier = this.identifier.semanticAnalysis();
		ss.caseBlock = this.caseBlock.semanticAnalysis(previousLabelNum);
		return ss;
	}
}
