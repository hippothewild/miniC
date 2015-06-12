package Absyn;

import java.io.PrintWriter;
import java.util.*;

public class Symbol extends Absyn {
    Type type;
    int length;
    RoleName role;
    int address;

    public Symbol(Type type, int length, RoleName role, int address) {
        this.type = type;
        this.length = length;
        this.role = role;
        this.address = address;
    }

    public TypeName getType() {
        if (this.type == null) {
            raiseError("Type error", "FATAL - Type not specified");
        }
        return type.typeName;
    }
}
