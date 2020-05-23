/* Generated By:JJTree: Do not edit this line. ASTEquals.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;
public
class ASTEquals extends SimpleNode {

  public ASTEquals(int id) {
    super(id);
  }

  public ASTEquals(Parser p, int id) {
    super(p, id);
  }

  public void analyzeSemantics(SymbolTable table) {
    String type1 = ((SimpleNode) children[0]).analyzeType(table);
    String type2 = ((SimpleNode) children[1]).analyzeType(table);
    if(!type1.equals(type2)) {
      System.out.println("Assignment expression in line " + getLine() + " is not valid\n");
      Parser.getInstance().addSemanticError();
      return;
    }
    if (((SimpleNode) children[1]).isInitialized(table)) {
      ((SimpleNode) children[0]).initialize(table);
    } else {
      System.out.println("The right side of assignment in line " + getLine() + " is not initialized\n");
      Parser.getInstance().addSemanticError();
    }
  }

  public int generateCode(SymbolTable table, PrintWriter print){

    SimpleNode right = ((SimpleNode) children[1]);
    int stackSize;

    if(children[0] instanceof ASTIntArray){
      ((SimpleNode) ((SimpleNode) children[0]).children[0]).generateCode(table, print);
      ((SimpleNode) ((SimpleNode) children[0]).children[1]).generateCode(table, print);
      stackSize = right.generateCode(table, print);
      print.println("\tiastore");
      return stackSize + 2;
    }

    ASTIdentifier left = ((ASTIdentifier) children[0]);
    String type = left.analyzeType(table);

    stackSize = right.generateCode(table, print);

    if(table.getSymbol(left.getName()).getAccess() == Symbol.Access.global){
      String className = Parser.getInstance().className;
      print.println("aload_0");
      print.println("\tputfield " + className + "/" + left.getName() +" "+ CodeGenerator.smallTypeFromString(type));
    }
    else if( ((type).equals("Int")) || ((type).equals("Bool")) ){
      print.print("\tistore ");
      print.println(table.getTablePosition(left.getName()));
    }
    else{
      print.print("\tastore ");
      print.println(table.getTablePosition(left.getName()));
    }

    return stackSize;
  }

}
/* JavaCC - OriginalChecksum=d522411537ae4a0e31477e9f193c7a0c (do not edit this line) */
