package Absyn;

import java.io.PrintWriter;
import java.util.*;

// Scope of parameters. (RoleName == `PARAMETER`)
public class FunctionScope {
    Type type;                          // Type of the function.
    String scopeName;                   // Name of the function.
    ArrayList<Symbol> paramList;        // List of parameters in the function.

    public FunctionScope(Type type, String scopeName) {
        this.type = type;
        this.scopeName = scopeName;
        this.paramList = new ArrayList<Symbol>();
    }

    public void addSymbol(Type type, int length) {
        Symbol symbol = new Symbol(type, length, RoleName.PARAMETER, 0);
        paramList.add(symbol);
    }
}
