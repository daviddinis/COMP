import java.util.ArrayList;
import java.io.*;

/* Generated By:JJTree: Do not edit this line. ASTIf.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTIf extends SimpleNode {
  public ASTIf(int id) {
    super(id);
  }

  public ASTIf(Parser p, int id) {
    super(p, id);
  }

  public void analyzeSemantics(SymbolTable table) {
    if (!((SimpleNode)children[0]).analyzeType(table).equals("Bool")) {
      System.err.println("If expression in line " + getLine() +" is not valid\n");
    }

    for (int i = 1; i < children.length; i++) {
      ((SimpleNode)children[i]).analyzeSemantics(table);
    }
  }

  public int generateCode(SymbolTable symbolTable, PrintWriter print){
    SimpleNode expression = ((SimpleNode) children[0]);
    SimpleNode then = ((SimpleNode) children[1]);
    SimpleNode else_ = ((SimpleNode) children[2]);
    int label = Parser.getAndIncLabel();
    String label_else = "else" + label; 
    String label_endif = "endif" + label; 


    int expStack = expression.generateCode(symbolTable, print);
    print.println("\tifeq " + label_else);
    int thenStack = then.generateCode(symbolTable, print);
    print.println("goto " + label_endif);
    print.println(label_else + ":");
    int elseStack = else_.generateCode(symbolTable, print);
    print.println(label_endif+ ":");

    return Math.max(Math.max(expStack, thenStack), elseStack);
  }
}
/* JavaCC - OriginalChecksum=3f17c4ed5b4fd5cc052c1c2d168b79b9 (do not edit this line) */
