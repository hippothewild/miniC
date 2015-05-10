package Absyn;

import java.util.*;

public class CompareExpr extends Expr {
    Expr lhs;
    Comparer comp;
    Expr rhs;

    public CompareExpr(Expr l, Comparer c, Expr r) {
        lhs = l;
        comp = c;
        rhs = r;
    }
}
