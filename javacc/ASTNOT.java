/* Generated By:JJTree: Do not edit this line. ASTNOT.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTNOT extends SimpleNode {
  public ASTNOT(int id) {
    super(id);
  }

  public ASTNOT(Parser p, int id) {
    super(p, id);
  }

  public String analyzeType(SymbolTable table) {
    if (((SimpleNode) children[0]).analyzeType(table).equals("Bool")) {
      return "Bool";
    }
    return "";
  }

  public boolean isInitialized(SymbolTable table) {
    return ((SimpleNode) children[0]).isInitialized(table);
  }
}
/* JavaCC - OriginalChecksum=16d53ed9e35a62bfcfc2dfb414cfb54a (do not edit this line) */
