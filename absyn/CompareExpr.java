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
        String floatPrefix = "";

        c.lhs = this.lhs.semanticAnalysis();
        blockIdx++;
        printWriter.println("    MOVE VR(0)@ VR(" + blockIdx + ")");

        c.rhs = this.rhs.semanticAnalysis();
        c.typeCheck();
        if (c.getType() == TypeName.FLOAT) {
            floatPrefix = "F";
        }

        switch (c.comp) {
        case EQ:
        printWriter.println("    " + floatPrefix + "SUB VR(" + blockIdx + ")@ VR(0)@ VR(0)");
        printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
        printWriter.println("    JMPZ VR(0)@ LABEL" + labelNum); // If true
        printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            break;
        case NOT_EQ:
            printWriter.println("    " + floatPrefix + "SUB VR(" + blockIdx + ")@ VR(0)@ VR(0)");
            printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            printWriter.println("    JMPZ VR(0)@ LABEL" + labelNum); // If false
            printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
            break;
        case LESS:
            printWriter.println("    " + floatPrefix + "SUB VR(" + blockIdx + ")@ VR(0)@ VR(0)");
            printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
            printWriter.println("    JMPN VR(0)@ LABEL" + labelNum); // If true
            printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            break;
        case GREATER_EQ:
            printWriter.println("    " + floatPrefix + "SUB VR(" + blockIdx + ")@ VR(0)@ VR(0)");
            printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            printWriter.println("    JMPN VR(0)@ LABEL" + labelNum); // If false
            printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
            break;
        case GREATER:
            printWriter.println("    " + floatPrefix + "SUB VR(0)@ VR(" + blockIdx + ")@ VR(0)");
            printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
            printWriter.println("    JMPN VR(0)@ LABEL" + labelNum); // If true
            printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            break;
        case LESS_EQ:
            printWriter.println("    " + floatPrefix + "SUB VR(0)@ VR(" + blockIdx + ")@ VR(0)");
            printWriter.println("    MOVE 0 VR(" + blockIdx + ")");
            printWriter.println("    JMPN VR(0)@ LABEL" + labelNum); // If true
            printWriter.println("    MOVE 1 VR(" + blockIdx + ")");
            break;
        }

        printWriter.println("LAB LABEL" + labelNum);
        printWriter.println("    MOVE VR(" + blockIdx + ")@ VR(0)");

        c.setType(TypeName.INT);
        blockIdx--;
        labelNum++;
        return c;
    }
}
