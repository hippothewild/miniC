package Absyn;

import java.util.*;

public class RetStmt extends Stmt {
    Expr expr;

    public RetStmt(Expr e) {
        expr = e;
    }

    public void printAST() {
        printWriter.print("return");
        if (expr != null) {
            printWriter.print(" ");
            expr.printAST();
        }
        printWriter.println(";");
    }

    public RetStmt semanticAnalysis() {
        RetStmt r = new RetStmt(null);
        r.expr = this.expr.semanticAnalysis();
        return r;
    }
}
