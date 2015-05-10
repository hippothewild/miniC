package Absyn;

import java.util.*;

public class Call {
    String name;
    ArgList args;

    public Call(String n, ArgList a) {
        name = n;
        args = a;
    }
}
