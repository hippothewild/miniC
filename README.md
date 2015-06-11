MiniC Parser & Compiler
=====================

This repo contains compiler of miniC grammar(http://www2.ufersa.edu.br/portal/view/uploads/setores/184/AppendixA.pdf), which is the term project in KAIST CS420(Compiler Design) course.

Build & Run
-------

To build code and generate parser,
```
$ make all
```

To test parser in STDIN,
```
$ make all
$ make run < (filename)
```

To test parser with sample code(sample.c),
```
$ make all
$ make test
```

To clean up the directory,
```
$ make vclean
```

AST & Symbol Table Results
-------
When you run the parser by `make run` or `make test`, it will print out AST generation and symbol table as two binary files:  
```
SYMBOL TABLE   : sym_table.out
AST GENERATION : ast.out
```
You can configure your own output settings in `absyn/Absyn.java`. Default setting is:
```java
static public boolean optionPrintAST      = true;
static public String  astOutputName       = "ast.out";
static public boolean optionPrintSymTable = true;
static public String  symTableOutputName  = "sym_table.out";
static public String  encoding            = "UTF-8";
```

Semantic Analysis
-------
The compiler will do semantic analysis on given input and raise compile error when needed.  

List of semantic analysis checklist :
- (Scope Check) Are all identifiers used after declared in correct scope?
- (Scope Check) Are all array identifiers and single identifiers are correctly referenced?
- (Scope Check) Are all functions and identifiers declared only once?
- (Scope Check) Does program have main() function in global scope?
- (Type Check) Are both side of binary/comparing expression proper type, int or float?
- (Type Check) Are all types of declared function parameters and following arguments matched?
- (Type Check) Is the type of array index int?
- (Type Check) Are all conditions in if/for/switch/while have proper type value? (int or float)
- (Type Check) Are all functions return appropriate typed value?
- (Type Check) Are all assign statements assign correct typed value for given identifiers?

Note - Some soft type conversions will be allowed but compiler will raise warn.
- Assigning int expression to float identifier
- Binary operation(+-*/) of int expression and float expression
- Comparing int expression and float expression

Reference
-------
Manual of CUP(in Korean): https://wiki.kldp.org/wiki.php/CUPManual  
Skeleton code with Java-CUP and JLex: https://github.com/gmh33/Java-CUP-jLex-Example  
Resolving Dangling-else Problem: http://www.parsifalsoft.com/ifelse.html  

Copyright
-------
Jihwan Chun  
<jihwan0321@kaist.ac.kr>  
Last update Jun-12-2015
