package Absyn;

import java.util.*;

public class ArgList extends Absyn {
    ArrayList<Expr> argList;

    public ArgList(Expr e) {
        argList = new ArrayList<Expr>();
        argList.add(e);
    }

    public void add(Expr e) {
        argList.add(e);
    }
}
