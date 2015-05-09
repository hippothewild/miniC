MiniC Parser & Compiler
=====================

This repo contains parser of miniC grammar(http://www2.ufersa.edu.br/portal/view/uploads/setores/184/AppendixA.pdf), which is part of the term project in KAIST CS420(Compiler Design) course.

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

Reference
-------
###### Project 01
Manual of CUP(in Korean): https://wiki.kldp.org/wiki.php/CUPManual  
Skeleton code with Java-CUP and JLex: https://github.com/gmh33/Java-CUP-jLex-Example  
Resolving Dangling-else Problem: http://www.parsifalsoft.com/ifelse.html  

Copyright
-------
Jihwan Chun  
<jihwan0321@kaist.ac.kr>  
Last update Apr-15-2015  
