package Absyn;

import java.util.*;

public class Assign extends Absyn {
    String name;
    Expr index, expr;

    public Assign(String n, Expr i, Expr e) {
        name = n;
        index = i;
        expr = e;
    }

    public void printAST() {
        printWriter.write(name);
        if (index != null) {
            printWriter.write("[");
            index.printAST();
            printWriter.write("]");
        }
        printWriter.write("=");
        expr.printAST();
    }

    public Assign semanticAnalysis() {
        Assign a = new Assign(name, null, null);
        if (index != null) {
            a.index = this.index.semanticAnalysis();
        }
        a.expr = this.expr.semanticAnalysis();
        return a;
    }
}
