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

        printWriter.println("    AREA SP");
        printWriter.println("    AREA FP");
        printWriter.println("    AREA VR");
        printWriter.println("    AREA MEM");
        printWriter.println("LAB START");
        printWriter.println("    MOVE END MEM(0)");
        printWriter.println("    MOVE 0 MEM(1)");
        printWriter.println("    MOVE 1 SP");
        printWriter.println("    MOVE 1 FP");

		Program p = new Program(null, null);
		if (this.declList != null) {
			p.declList = this.declList.semanticAnalysis();
		}

        push("END");
        push(0);
        printWriter.println("    MOVE SP@ FP");
        printWriter.println("    JMP main");

        if (this.funcList != null) {
			p.funcList = this.funcList.semanticAnalysis();
		}

		// Scope-out one step.
		popSymbolScope();

        if (getFunctionScope("main") == null) {
            raiseError(SEMANTIC_ERR, "Program should have main() function.");
        }

        printWriter.println("LAB END");
		return p;
    }
}
