package Absyn;

import java.util.*;

public class IdExpr extends Expr {
    String name;
    boolean isArray;
    Expr index;

    public IdExpr(String n) {
        name = n;
        isArray = false;
    }

    public IdExpr(String n, Expr i) {
        name = n;
        index = i;
        isArray = true;
    }

    public void printAST() {
        printWriter.print(name);
        if (isArray) {
            printWriter.printf("[");
            index.printAST();
            printWriter.printf("]");
        }
    }

    public IdExpr semanticAnalysis() {
        // Do semantic check for given identifier.
        Symbol sym = getSymbolFromSymbolTable(this.name);
        if (sym == null) {
            raiseError(SEMANTIC_ERR, "Variable " + this.name + " is not declared.");
        }
        if (this.isArray) {
            if (sym.length == 0) {
                raiseError(SEMANTIC_ERR, "Referenced index of non-array variable " + this.name + ".");
            }
        }

        IdExpr i = new IdExpr(this.name);
        if (this.isArray) {
            i.index = this.index.semanticAnalysis();
            i.isArray = true;
        }
        return i;
    }
}
