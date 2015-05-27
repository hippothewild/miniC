package Absyn;

import java.util.*;

public class CompareExpr extends Expr {
    Expr lhs;
    Comparer comp;
    Expr rhs;
    boolean typeMismatched;

    public CompareExpr(Expr l, Comparer c, Expr r) {
        lhs = l;
        comp = c;
        rhs = r;
        typeMismatched = false;
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

    private void typeCheck() {
        if(lhs.getType() != rhs.getType()) {
            raiseWarn("Type mismatched, this compare expression should have same type at both side");
            this.setType(TypeName.FLOAT);
            this.typeMismatched = true;
        } else {
            this.setType(lhs.getType());
            this.typeMismatched = false;
        }
    }

    public CompareExpr semanticAnalysis() {
        CompareExpr c = new CompareExpr(null, this.comp, null);
        c.lhs = this.lhs.semanticAnalysis();
        c.rhs = this.rhs.semanticAnalysis();
        c.typeCheck();
        return c;
    }
}
