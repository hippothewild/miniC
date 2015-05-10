package Absyn;

import java.util.*;

public class Param {
    Type type;
    Identifier identifier;

    public Param(Type t, Identifier i) {
        type = t;
        identifier = i;
    }
}
