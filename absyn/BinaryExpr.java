package Absyn;

import java.util.*;

public class BinaryExpr extends Expr {
    Expr lhs;
    BinaryOperator binOp;
    Expr rhs;

    public BinaryExpr(Expr l, BinaryOperator b, Expr r) {
        lhs = l;
        binOp = b;
        rhs = r;
    }

    public void printAST() {
        lhs.printAST();
        switch (binOp) {
        case PLUS:
            printWriter.print("+");
            break;
        case MINUS:
            printWriter.print("-");
            break;
        case DIVIDE:
            printWriter.print("/");
            break;
        case TIMES:
            printWriter.print("*");
            break;
        }
        rhs.printAST();
    }

    public BinaryExpr semanticAnalysis() {
        BinaryExpr b = new BinaryExpr(null, binOp, null);
        b.lhs = this.lhs.semanticAnalysis();
        b.rhs = this.rhs.semanticAnalysis();
        return b;
    }
}
