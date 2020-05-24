/* Generated By:JJTree: Do not edit this line. ASTLENGTH.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;

public class ASTLENGTH extends SimpleNode {
  public ASTLENGTH(int id) {
    super(id);
  }

  public ASTLENGTH(Parser p, int id) {
    super(p, id);
  }

  public String analyzeType(SymbolTable table) {
    if (((SimpleNode)children[0]).analyzeType(table).equals("Int[]")) {
      return "Int";
    }
    return "";
  }


  public boolean isInitialized(SymbolTable table) {
    return ((SimpleNode) children[0]).isInitialized(table);
  }

  public int generateCode(SymbolTable symbolTable, PrintWriter print){
      int stackSize = ((SimpleNode)children[0]).generateCode(symbolTable, print);
      print.println("\tarraylength ");
      return stackSize;
  }

}
/* JavaCC - OriginalChecksum=9340b1c9d0f3a7601be1ff8cdf1361a0 (do not edit this line) */
