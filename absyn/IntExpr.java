package Absyn;

import java.util.*;

public class IntExpr extends Expr {
    int num;

    public IntExpr(int n) {
        num = n;
    }

    public void printAST() {
        printWriter.print(num);
    }
}
