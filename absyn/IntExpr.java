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
}
