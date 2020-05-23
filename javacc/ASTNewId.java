import java.io.PrintWriter;

/* Generated By:JJTree: Do not edit this line. ASTNewId.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTNewId extends SimpleNode {
  public ASTNewId(int id) {
    super(id);
  }

  public ASTNewId(Parser p, int id) {
    super(p, id);
  }

  public String analyzeType(SymbolTable table) {
    return ((ASTIdentifier) children[0]).getType();
  }

  public boolean isInitialized(SymbolTable table) {
    return true;
  }

  public int generateCode(SymbolTable table, PrintWriter print) {
    int arguments = 0;
    if (analyzeType(table).equals(Parser.getInstance().className)) {
      print.println("\tnew " + analyzeType(table));
      print.println("\tdup");
  
      if (((SimpleNode)children[1]).children != null) {

        for (int i = 0; i < ((ASTArgumentCall) children[1]).children.length; i++) {
          arguments++;
          ((SimpleNode) ((ASTArgumentCall) children[1]).children[i]).generateCode(table, print);
        }
      }
      print.print("\tinvokespecial " + analyzeType(table) + "/<init>(");
      if (((SimpleNode)children[1]).children != null) {
        for (int i = 0; i < ((ASTArgumentCall) children[1]).children.length; i++) {
          String type = CodeGenerator.smallTypeFromString(((SimpleNode) ((ASTArgumentCall) children[2]).children[i]).analyzeType(table));
          print.print(type);
        }
      }
      print.println(")V");
      
    }
    return arguments+2;
  }
}
/*
 * JavaCC - OriginalChecksum=1c5ecce8b399f06df2cfddd4cdce53c6 (do not edit this
 * line)
 */
