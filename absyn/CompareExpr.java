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
        if (lhs.getType() != TypeName.INT && lhs.getType() != TypeName.FLOAT) {
            raiseError(TYPE_ERR, "Error in compare expression - left hand side expects float or int type, but got other type.");
        }
        if (rhs.getType() != TypeName.INT && rhs.getType() != TypeName.FLOAT) {
            raiseError(TYPE_ERR, "Error in compare expression - right hand side expects float or int type, but got other type.");
        }

        if (this.comp == Comparer.EQ || this.comp == Comparer.NOT_EQ) {
            // '==' or '!=' operator; lhs and rhs must be exactly same type.
            if (lhs.getType() != rhs.getType()) {
                raiseError(TYPE_ERR, "Type mismatched, this compare expression should have same type at both side.");
            } else {
                this.typeMismatched = false;
                this.setType(TypeName.INT); // Result of compare will be -1, 0, or 1.
            }
        } else {
            if(lhs.getType() != rhs.getType()) {
                raiseWarn("Type mismatched, this compare expression should have same type at both side");
                this.typeMismatched = true;
            } else {
                this.typeMismatched = false;
            }

            this.setType(TypeName.INT); // Result of compare will be -1, 0, or 1.
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
