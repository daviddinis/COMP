/* Generated By:JJTree: Do not edit this line. ASTWhileExpression.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;

public
class ASTWhileExpression extends SimpleNode {
  public ASTWhileExpression(int id) {
    super(id);
  }

  public ASTWhileExpression(Parser p, int id) {
    super(p, id);
  }

  public void analyzeSemantics(SymbolTable table) {
    if (!((SimpleNode)children[0]).analyzeType(table).equals("Bool")) {
      System.err.println("While expression in line " + getLine() +" is not valid\n");
    }

    for (int i = 1; i < children.length; i++) {
      ((SimpleNode)children[i]).analyzeSemantics(table);
    }
  }


  public int generateCode(SymbolTable symbolTable, PrintWriter print){
    if (children == null) return -1;

    int stackSize = 0;
    for (int i = 0; i < children.length; i++) {
      stackSize = ((SimpleNode) children[i]).generateCode(symbolTable, print);
    }

    return stackSize;
  }
}
/* JavaCC - OriginalChecksum=a9e3b1f68dbe03321e86491d5d7e8b24 (do not edit this line) */
