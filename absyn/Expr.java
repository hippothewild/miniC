package Absyn;

import java.util.*;

public class Expr extends Absyn {
    Type type;

    /*
     * Expr.semanticAnalysis() will cover all sAnalysis functions for misc. expressions
     */
    public Expr semanticAnalysis() {
        return this;
    }

    public void setType(TypeName t) {
        type = new Type(t);
    }

    public TypeName getType() {
        if (type == null) {
            raiseError(TYPE_ERR, "FATAL - Type not specified");
        }
        return this.type.typeName;
    }
}
