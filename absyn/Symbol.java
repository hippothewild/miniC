package Absyn;

import java.io.PrintWriter;
import java.util.*;

public class Symbol extends Absyn {
    Type type;
    int length;
    RoleName role;

    public Symbol(Type type, int length, RoleName role) {
        this.type = type;
        this.length = length;
        this.role = role;
    }

    public TypeName getType() {
        if (this.type == null) {
            raiseError("Type error", "FATAL - Type not specified");
        }
        return type.typeName;
    }
}
