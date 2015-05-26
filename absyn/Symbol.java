package Absyn;

import java.io.PrintWriter;
import java.util.*;

public class Symbol {
    Type type;
    int length;
    RoleName role;

    public Symbol(Type type, int length, RoleName role) {
        this.type = type;
        this.length = length;
        this.role = role;
    }
}
