package Absyn;

import java.util.*;

public class ParenExpr extends Expr {
    Expr expr;

    public ParenExpr(Expr e) {
        expr = e;
    }

    public void printAST() {
        printWriter.print("(");
        expr.printAST();
        printWriter.print(")");
    }
}
