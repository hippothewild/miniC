package Absyn;

import java.util.*;

public class Program extends Absyn {
    DeclList declList = null;
    FuncList funcList = null;

    public Program(DeclList d, FuncList f) {
        declList = d;
        funcList = f;
    }

    public void printAST() {
        if (declList != null) {
            declList.printAST();
        }
        if (funcList != null) {
            funcList.printAST();
        }
    }

    public void printSymTable() {
        printSymTableHeader();
        if (declList != null) {
            declList.printSymTable();
        }
        if (funcList != null) {
            funcList.printSymTable();
        }
    }

    public Program semanticAnalysis() {
        // Scope-in one step. (function)
		pushSymbolScope(GLOBAL);

		Program p = new Program(null, null);
		if (this.declList != null) {
			p.declList = this.declList.semanticAnalysis();
		}
        if (this.funcList != null) {
			p.funcList = this.funcList.semanticAnalysis();
		}

		// Scope-out one step.
		popSymbolScope();

        if (getFunctionScope("main") == null) {
            raiseError(SEMANTIC_ERR, "Program should have main() function.");
        }
		return p;
    }
}
