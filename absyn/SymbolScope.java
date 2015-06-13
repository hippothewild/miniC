package Absyn;

import java.io.PrintWriter;
import java.util.*;

// Scope of variables. (RoleName == `VARIABLE`)
public class SymbolScope {
    String scopeName;                   // Name of the scope. (`GLOBAL`, `COMPOUND`, function name)
    HashMap<String, Symbol> symbolMap;  // List of symbols in the scope.

    public SymbolScope(String scopeName) {
        this.scopeName = scopeName;
        this.symbolMap = new HashMap<String, Symbol>();
    }

    public void addSymbol(String symbolName, Type type, int length, int address) {
        Symbol symbol;
        if (this.scopeName.equals("GLOBAL")) {
            symbol = new Symbol(type, length, RoleName.GLOBAL, address);
        } else {
            symbol = new Symbol(type, length, RoleName.VARIABLE, address);
        }
        symbolMap.put(symbolName, symbol);
    }

    public boolean contains(String symbolName) {
        return this.symbolMap.containsKey(symbolName);
    }
}
