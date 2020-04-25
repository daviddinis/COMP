/* Generated By:JJTree: Do not edit this line. ASTAnd.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTAnd extends SimpleNode {
  public ASTAnd(int id) {
    super(id);
  }

  public ASTAnd(Parser p, int id) {
    super(p, id);
  }

  public String analyzeType(SymbolTable table) {
    // verify right side
    String type1 = ((SimpleNode)children[0]).analyzeType(table);
    String type2 = ((SimpleNode)children[1]).analyzeType(table);
    if(type1.equals("Bool") && type2.equals("Bool")) {
      return "Bool";
    }
    else return "";
  }

  public boolean isInitialized(SymbolTable table) {
    return ((SimpleNode)children[0]).isInitialized(table) && ((SimpleNode)children[1]).isInitialized(table); 
  }
}
/* JavaCC - OriginalChecksum=1766d5431be81e19119cf5feee80cd49 (do not edit this line) */
