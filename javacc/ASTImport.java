/* Generated By:JJTree: Do not edit this line. ASTImport.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTImport extends SimpleNode {

  public boolean isStatic;
  public boolean dot;
  public boolean returnType;

  public ASTImport(int id) {
    super(id);
    isStatic = false;
    dot = false;
    returnType = false;
  }

  public ASTImport(Parser p, int id) {
    super(p, id);
    isStatic = false;
    dot = false;
    returnType = false;
  }

  public void createSymbolTable(SymbolTable symbolTable) {

    if (children != null && children.length > 1) {

      SymbolTable newTable = new SymbolTable(symbolTable);
      String methodName = new String();
      if (isStatic) {
        //methodName += "static ";
        newTable.setStatic(isStatic);
        Symbol s = new Symbol(((ASTIdentifier) children[0]).getName(), ((ASTIdentifier) children[0]).getName(), Symbol.Access.global);
        symbolTable.addSymbol(s);
      }

      methodName += ((ASTIdentifier) children[0]).getName();
      if (dot) {
        methodName += "." + ((ASTIdentifier) children[1]).getName();
      }
      methodName += "(";
      for (int i = 0; i < children.length; i++) {
        if (children[i] instanceof ASTParamList) {
          if (((ASTParamList) children[i]).children != null) {
            methodName += ((ASTType) ((ASTParamList) children[i]).children[0]).getType();

            for (int k = 1; k < ((ASTParamList) children[i]).children.length; k++) {
              methodName += ",";
              methodName += ((ASTType) ((ASTParamList) children[i]).children[k]).getType();
            }
          }
          methodName += ")";
          if (returnType) {
            newTable.setReturnType(((ASTType) children[i + 1]).getType());
          }else{
            newTable.setReturnType("void");
          }
          break;
        }
      }

      Parser.getInstance().addMethod(methodName, newTable);

    }
    else {
      Parser.getInstance().addType(((ASTIdentifier) children[0]).getName());
    }
  }

  public void analyzeSemantics(SymbolTable table) {
    
  }

}
/*
 * JavaCC - OriginalChecksum=102b8623e78d8554f34992788a15d8e1 (do not edit this
 * line)
 */
