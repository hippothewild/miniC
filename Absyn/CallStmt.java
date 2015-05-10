package Absyn;

import java.util.*;

public class CallStmt extends Stmt {
    Call call;

    public CallStmt(Call c) {
        call = c;
    }
}
