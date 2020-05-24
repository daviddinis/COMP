/* Generated By:JJTree: Do not edit this line. ASTReturn.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;
public class ASTReturn extends SimpleNode {
  public ASTReturn(int id) {
    super(id);
  }

  public ASTReturn(Parser p, int id) {
    super(p, id);
  }

  public void analyzeSemantics(SymbolTable table) {
    String type = ((SimpleNode) children[0]).analyzeType(table);
    if (!table.getReturnType().equals(type)) {
      System.out.println("Return type in line " + getLine() + " is not valid");
      Parser.getInstance().addSemanticError();
    }
  }

  public int generateCode(SymbolTable table, PrintWriter print){

    int stackSize = ((SimpleNode)this.children[0]).generateCode(table, print);

    String returnType = table.getReturnType();
    String smallReturn;
    switch (returnType) {
      case "Int":
          smallReturn = "i";
          break;
      case "Bool":
          smallReturn = "i";
          break;
      case "Int[]":
          smallReturn = "a";
          break;
      case "void":
          smallReturn = "";
          break;
      default:
          smallReturn=  "a";
          break;
    }
    print.println("\t" + smallReturn+ "return");

    return stackSize;
  }


}
/*
 * JavaCC - OriginalChecksum=0d7badc8de429fd7f39c8b2723d9a136 (do not edit this
 * line)
 */
