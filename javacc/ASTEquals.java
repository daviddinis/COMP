/* Generated By:JJTree: Do not edit this line. ASTEquals.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
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
    System.out.println(type1 + " " + type2);
    if(!type1.equals(type2)) {
      System.err.println("Assignment expression in line " + getLine() + " is not valid\n");
      return;
    }
    if (((SimpleNode) children[1]).isInitialized(table)) {
      ((SimpleNode) children[0]).initialize(table);
    } else {
      System.out.println("The left side of assignment in line " + getLine() + " is not initialized\n");
    }
  }

}
/* JavaCC - OriginalChecksum=d522411537ae4a0e31477e9f193c7a0c (do not edit this line) */
