package Absyn;

import java.util.*;

public class BinaryExpr extends Expr {
    Expr lhs;
    BinaryOperator binOp;
    Expr rhs;
    boolean typeMismatched;

    public BinaryExpr(Expr l, BinaryOperator b, Expr r) {
        lhs = l;
        binOp = b;
        rhs = r;
        typeMismatched = false;
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

    private void typeCheck() {
        if(lhs.getType() != rhs.getType()) {
            raiseWarn("Type mismatched, this binary expression should have same type at both side");
            this.setType(TypeName.FLOAT);
            this.typeMismatched = true;
        } else {
            this.setType(lhs.getType());
            this.typeMismatched = false;
        }
    }

    public BinaryExpr semanticAnalysis() {
        BinaryExpr b = new BinaryExpr(null, binOp, null);
        b.lhs = this.lhs.semanticAnalysis();
        b.rhs = this.rhs.semanticAnalysis();
        b.typeCheck();
        return b;
    }
}
