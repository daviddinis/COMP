/* Generated By:JJTree: Do not edit this line. ASTLessThan.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;

public
class ASTLessThan extends SimpleNode {
  public ASTLessThan(int id) {
    super(id);
  }

  public ASTLessThan(Parser p, int id) {
    super(p, id);
  }

  public String analyzeType(SymbolTable table) {
    String type1 = ((SimpleNode)children[0]).analyzeType(table);
    String type2 = ((SimpleNode)children[1]).analyzeType(table);
    if(type1.equals("Int") && type2.equals("Int")) {
      return "Bool";
    }
    else return "";
  }

  public boolean isInitialized(SymbolTable table) {
    return ((SimpleNode)children[0]).isInitialized(table) && ((SimpleNode)children[1]).isInitialized(table); 
  }

  public int generateCode(SymbolTable symbolTable, PrintWriter print){
    SimpleNode left = ((SimpleNode) children[0]);
    SimpleNode right = ((SimpleNode) children[1]);

    int stackLeft = left.generateCode(symbolTable, print);
    int stackRight = right.generateCode(symbolTable, print) + 1;

    int stackSize = Math.max(stackRight, stackLeft);

    int label = Parser.getAndIncLabel();
    String label_else = "else" + label; 
    String label_endif = "endif" + label; 

    
    print.println("\tif_icmpge " + label_else);
    print.println("\ticonst_1");
    print.println("\tgoto " + label_endif);
    print.println(label_else + ":");
    print.println("\ticonst_0");
    print.println(label_endif+ ":");

    return stackSize;
  }
}
/* JavaCC - OriginalChecksum=c0d8a8972c0a4b4df64282733df15850 (do not edit this line) */
