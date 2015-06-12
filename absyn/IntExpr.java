package Absyn;

import java.util.*;

public class IntExpr extends Expr {
    int num;

    public IntExpr(int n) {
        num = n;
        this.setType(TypeName.INT);
    }

    public void printAST() {
        printWriter.print(num);
    }

    public IntExpr semanticAnalysis() {
        printWriter.println("    MOVE " + this.num + " VR(0)");
        return this;
    }
}
