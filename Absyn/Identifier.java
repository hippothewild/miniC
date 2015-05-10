package Absyn;

import java.util.*;

public class Identifier {
    String name;
    boolean isArray;
    int size;

    public Identifier(String n, boolean i) {
        name = n;
        isArray = false;
    }

    public Identifier(String n, boolean i, int s) {
        name = n;
        isArray = true;
        size = s;
    }
}
