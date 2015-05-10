package Absyn;

import java.util.*;

public class CallExpr extends Expr {
    Call call;

    public CallExpr(Call c) {
        call = c;
    }
}
