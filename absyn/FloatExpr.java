package Absyn;

import java.util.*;

public class FloatExpr extends Expr {
    float num;

    public FloatExpr(float n) {
        num = n;
        this.setType(TypeName.FLOAT);
    }

    public void printAST() {
        printWriter.print(num);
    }

    public FloatExpr semanticAnalysis() {
        printWriter.println("    MOVE " + this.num + " VR(0)");
        return this;
    }
}
