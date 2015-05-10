package Absyn;

import java.util.*;

public class RetStmt extends Stmt {
    Expr expr;

    public RetStmt(Expr e) {
        expr = e;
    }
}
