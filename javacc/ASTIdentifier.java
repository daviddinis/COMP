
/* Generated By:JJTree: Do not edit this line. ASTIdentifier.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTIdentifier extends SimpleNode implements ASTType{
  public ASTIdentifier(int id) {
    super(id);
  }

  public ASTIdentifier(Parser p, int id) {
    super(p, id);
  }

  public String getType(){
    return "" + jjtGetValue();
  }

  public String getName(){
    return "" + jjtGetValue();
  }

  public void analyzeSemantics(SymbolTable symbolTable){

      
  }
}
/* JavaCC - OriginalChecksum=75abfe948da46b0c7b3a59b2cae3a666 (do not edit this line) */
