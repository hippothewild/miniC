package Absyn;

import java.io.PrintWriter;
import java.util.*;

// Scope of parameters. (RoleName == `PARAMETER`)
public class FunctionScope {
    Type type;                          // Type of the function.
    String scopeName;                   // Name of the function.
    HashMap<String, Symbol> symbolMap;  // List of symbols in the function.

    public FunctionScope(Type type, String scopeName) {
        this.type = type;
        this.scopeName = scopeName;
        this.symbolMap = new HashMap<String, Symbol>();
    }

    public void addSymbol(String symbolName, Type type, int length) {
        Symbol symbol = new Symbol(type, length, RoleName.PARAMETER);
        symbolMap.put(symbolName, symbol);
    }
}
