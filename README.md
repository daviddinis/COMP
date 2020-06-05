 ## A compiler of Java-- programs to Java bytecodes
##### GROUP: 4D
NAME1: David Dinis, NR1: up201706766, GRADE1: 18, CONTRIBUTION1: 25%  
NAME2: Henrique Freitas, NR2: up201707046, GRADE2: 18, CONTRIBUTION2: 25%  
NAME3: Inês Alves, NR3: up201605335, GRADE3: 18, CONTRIBUTION3: 25%  
NAME4: José Gomes, NR4: up201707054, GRADE4: 18, CONTRIBUTION4: 25%  

GLOBAL Grade of the project: 18  
## SUMMARY:
 Our project is specialized in compiling a file in java-- language program into java bytes. Taking a Java-- (.jmm) file as input, the program outputs a compilable(syntax and semantic error free) jasmin file (.j).
 
EXECUTE:  
``` code 
gradle build
java -jar comp2020-4d.jar <jmm file> [(1: debug prints | 0: default) [1: ignore initialization warning | 0: default]]
java -jar jasmin.jar <jasmin file>
java <className>
```
or use the provided bash script "compileNRun.sh" (Beware of file structure)
```code
gradle build
bash compileNrun.sh <className>
```
**Note:** Compiled jasmin files (.j) will go to the "jasminFiles" directory.

### DEALING WITH SYNTACTIC ERRORS:  
The program catches up to 10 syntatic errors present in the input source file if they are present inside the conditional expression of a while statement.
The remaining posible errors throw an exception indicating the type of error and where in the file it occured (line and column).  

### SEMANTIC ANALYSIS:
In the semantic analysis all the assignments and function calls are verified for variable initialization and type validation. Each node in the tree is responsible for knowing its type and inform the parent node about it so that all instructions chain together properly.
In case of initialization inside an unknown scope, the compiler shows a warning indicating the variable might not be initialized.
If the program is called with initalization warning set to 1 all initializations checks are not performed.

### INTERMEDIATE REPRESENTATIONS (IRs): 
The program utilises an Abstract Syntax Tree (AST) and a Symbol Table to aid in the semantic analysis and code generation proccesses.

### CODE GENERATION:
Each node of the AST is responsible for its own code generation and the actual write proccess to the jasmin file.
The CodeGenerator class starts the main processing of the class (methods and fields) but later the responsibility is passed on to the function nodes that recursively delegate the writting task to their children nodes.

### OVERVIEW: 
As stated previously our approach relies heavily on the AST to delegate the important tasks to each node and thus iterate through the tree recursively in a depth-first kind of way.
The Sethi-Ullman algorithm was used in order to calculate the maximum necessary and minimum possible stack size.
  

### TASK DISTRIBUTION: 
Our team relied heavily on Pair-Programming to develop this project. As such we believe that each individual member contributed to every aspect of the project.

### PROS:
 Our tool is able to perform every basic task required in the projects specification and has some extra grammatical features that were not requested.   
    - Using object instances as arguments;  
    - Return object instances;  


### CONS:
 Our tool does not implement the '-r' and '-o' optimizations. Furthermore, our implementation of conditional expressions, in spite of working as intended, produces an extensive amount of code that can be hard to read and could be shortened.