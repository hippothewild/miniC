package Absyn;

import java.util.*;

public class RetStmt extends Stmt {
    Expr expr;

    public RetStmt(Expr e) {
        expr = e;
    }

    public void printAST() {
        printWriter.print("return");
        if (expr != null) {
            printWriter.print(" ");
            expr.printAST();
        }
        printWriter.println(";");
    }

    public RetStmt semanticAnalysis() {
        RetStmt r = new RetStmt(null);
        if (this.expr == null) {
            raiseError(SEMANTIC_ERR, "Function " + getCurrentFunctionScope().scopeName + " returns nothing.");
        } else {
            r.expr = this.expr.semanticAnalysis();
            printWriter.println("    MOVE FP@ SP");
            printWriter.println("    MOVE MEM(FP@)@ FP");
            printWriter.println("    SUB SP@ 1 SP");
            printWriter.println("    JMP MEM(SP@)@");
        }

        // Type check return expr's type and current function's expected return type.
        Type expectedType = getCurrentFunctionScope().type;
        Type retType = r.expr.type;
        if (expectedType.typeName != retType.typeName) {
            raiseError(TYPE_ERR, "Function "
                                    + getCurrentFunctionScope().scopeName
                                    + " expects "
                                    + expectedType.typeStr()
                                    + " as a return type but got "
                                    + retType.typeStr()
                                    + ".");
        }

        return r;
    }
}
