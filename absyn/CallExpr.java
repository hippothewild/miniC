package Absyn;

import java.util.*;

public class CallExpr extends Expr {
    Call call;

    public CallExpr(Call c) {
        call = c;
    }

    public void printAST() {
        call.printAST();
    }

    public CallExpr semanticAnalysis() {
        CallExpr c = new CallExpr(null);
        c.call = this.call.semanticAnalysis();
        c.setType(c.call.getType());
        return c;
    }
}
