package Absyn;

import java.util.*;

public class CompareExpr extends Expr {
    Expr lhs;
    Comparer comp;
    Expr rhs;

    public CompareExpr(Expr l, Comparer c, Expr r) {
        lhs = l;
        comp = c;
        rhs = r;
    }

    public void printAST() {
        lhs.printAST();
        switch (comp) {
        case LESS:
            printWriter.print("<");
            break;
        case GREATER:
            printWriter.print(">");
            break;
        case LESS_EQ:
            printWriter.print("<=");
            break;
        case GREATER_EQ:
            printWriter.print(">=");
            break;
        case EQ:
            printWriter.print("==");
            break;
        case NOT_EQ:
            printWriter.print("!=");
            break;
        }
        rhs.printAST();
    }

    public CompareExpr semanticAnalysis() {
        CompareExpr c = new CompareExpr(null, this.comp, null);
        c.lhs = this.lhs.semanticAnalysis();
        c.rhs = this.rhs.semanticAnalysis();
        return c;
    }
}
