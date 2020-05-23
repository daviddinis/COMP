/* Generated By:JJTree: Do not edit this line. ASTVariable.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.*;

public class ASTVariable extends SimpleNode {
  public ASTVariable(int id) {
    super(id);
  }

  public ASTVariable(Parser p, int id) {
    super(p, id);
  }


  public void createSymbolTable(SymbolTable symbolTable){
    String type;
    type = ((ASTType)children[0]).getType();
    if (!Parser.getInstance().checkType(type)) {
      System.out.println("Type " + type + " in line " +  getLine() + "is not valid!");
      Parser.getInstance().addSemanticError();
      return;
    }
    Symbol symbol;
    if (parent instanceof ASTClassDeclaration) {
      symbol = new Symbol(type, ((ASTIdentifier)children[1]).getName(), Symbol.Access.global);
    } else {
      symbol = new Symbol(type, ((ASTIdentifier)children[1]).getName(), Symbol.Access.local);
    }
    symbolTable.addSymbol(symbol);
  }

}
/* JavaCC - OriginalChecksum=11009f23ff9dd66ec04efe02c564fc61 (do not edit this line) */
