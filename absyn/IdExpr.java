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
        IdExpr i = new IdExpr(this.name);
        i.index = this.index.semanticAnalysis();
        return i;
    }
}
