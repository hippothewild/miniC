package Absyn;

import java.util.*;

public class Identifier extends Absyn {
    String name;
    boolean isArray;
    int size;

    public Identifier(String n) {
        name = n;
        isArray = false;
    }

    public Identifier(String n, int s) {
        name = n;
        isArray = true;
        size = s;
    }
}
