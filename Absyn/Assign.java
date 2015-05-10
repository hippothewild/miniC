package Absyn;

import java.util.*;

public class Assign extends Absyn {
    String name;
    Expr index, expr;

    public Assign(String n, Expr i, Expr e) {
        name = n;
        index = i;
        expr = e;
    }
}
