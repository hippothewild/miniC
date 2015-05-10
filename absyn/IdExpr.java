package Absyn;

import java.util.*;

public class IdExpr extends Expr {
    String name;
    boolean isArray;
    Expr index;

    public IdExpr(String n) {
        name = n;
        isArray = false;
    }

    public IdExpr(String n, Expr i) {
        name = n;
        index = i;
        isArray = true;
    }
}
