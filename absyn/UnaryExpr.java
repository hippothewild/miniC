package Absyn;

import java.util.*;

public class UnaryExpr extends Expr {
    // UnaryExpr = -(Expr)
    Expr expr;

    public UnaryExpr(Expr e) {
        expr = e;
    }

    public void printAST() {
        printWriter.print("-");
        expr.printAST();
    }

    public UnaryExpr semanticAnalysis() {
        UnaryExpr u = new UnaryExpr(null);
        u.expr = this.expr.semanticAnalysis();
        return u;
    }
}
