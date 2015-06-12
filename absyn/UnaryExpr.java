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
        if (u.expr.getType() == TypeName.INT) {
            printWriter.println("    SUB 0 VR(0)@ VR(0)");
        } else if (u.expr.getType() == TypeName.FLOAT) {
            printWriter.println("    FSUB 0 VR(0)@ VR(0)");
        } else {
            raiseError(SEMANTIC_ERR, "Unary expression can only take single float or int type.");
        }

        u.setType(u.expr.getType());
        return u;
    }
}
