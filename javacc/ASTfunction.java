
/* Generated By:JJTree: Do not edit this line. ASTfunction.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
import java.io.PrintWriter;

public class ASTfunction extends SimpleNode {
  private String methodName;

  public ASTfunction(int id) {
    super(id);
    methodName = "";
  }

  public ASTfunction(Parser p, int id) {
    super(p, id);
    methodName = "";
  }

  public String analyzeType(SymbolTable table) {
    Boolean primeiro = ((SimpleNode) children[0]).analyzeType(table).equals("this");
    Boolean segundo = ((SimpleNode) children[0]).analyzeType(table).equals(Parser.getInstance().className);
    if (primeiro || segundo) {
      methodName = ((ASTIdentifier) children[1]).getName();
      methodName += "(";
      if (((ASTArgumentCall) children[2]).children != null) {
        for (int i = 0; i < ((ASTArgumentCall) children[2]).children.length; i++) {
          methodName += ((SimpleNode) ((ASTArgumentCall) children[2]).children[i]).analyzeType(table);
          if (i != ((ASTArgumentCall) children[2]).children.length - 1)
            methodName += ",";
        }
      }
      methodName += ")";
      if (Parser.getInstance().getTable(methodName) != null) {

        return Parser.getInstance().getTable(methodName).getReturnType();
      } else if (Parser.getInstance().extend != null
          && Parser.getInstance().getTable(Parser.getInstance().extend + "." + methodName) != null) {
        methodName = Parser.getInstance().extend + "." + methodName;
        return Parser.getInstance().getTable(methodName).getReturnType();
      } else
        System.out.println("Function call " + methodName + " is not valid");
      return "";
    } else {
      methodName = ((SimpleNode) children[0]).analyzeType(table) + ".";
      methodName += ((ASTIdentifier) children[1]).getName() + "(";
      if (((ASTArgumentCall) children[2]).children != null) {
        for (int i = 0; i < ((ASTArgumentCall) children[2]).children.length; i++) {
          methodName += ((SimpleNode) ((ASTArgumentCall) children[2]).children[i]).analyzeType(table);
          if (i != ((ASTArgumentCall) children[2]).children.length - 1)
            methodName += ",";
        }
      }
      methodName += ")";
      if (Parser.getInstance().getTable(methodName) != null) {
        return Parser.getInstance().getTable(methodName).getReturnType();
      } else {
        System.out.println("Function call " + methodName + " is not valid");
        return "";
      }
    }
  }

  public boolean isInitialized(SymbolTable table) {
    return true;
  }

  public void analyzeSemantics(SymbolTable table) {
    Boolean primeiro = ((SimpleNode) children[0]).analyzeType(table).equals("this");
    Boolean segundo = ((SimpleNode) children[0]).analyzeType(table).equals(Parser.getInstance().className);
    if (primeiro || segundo) {
      methodName = ((ASTIdentifier) children[1]).getName();
      methodName += "(";
      if (((ASTArgumentCall) children[2]).children != null) {
        for (int i = 0; i < ((ASTArgumentCall) children[2]).children.length; i++) {
          methodName += ((SimpleNode) ((ASTArgumentCall) children[2]).children[i]).analyzeType(table);
          if (i != ((ASTArgumentCall) children[2]).children.length - 1)
            methodName += ",";
        }
      }
      methodName += ")";
      if (Parser.getInstance().getTable(methodName) != null) {

      } else if (Parser.getInstance().extend != null
          && Parser.getInstance().getTable(Parser.getInstance().extend + "." + methodName) != null) {
        methodName = Parser.getInstance().extend + "." + methodName;
      } else {
        System.out.println("Function call " + methodName + " is not valid");
        Parser.getInstance().addSemanticError();
      }
    } else {
      methodName = ((SimpleNode) children[0]).analyzeType(table) + ".";
      methodName += ((ASTIdentifier) children[1]).getName() + "(";
      if (((ASTArgumentCall) children[2]).children != null) {
        for (int i = 0; i < ((ASTArgumentCall) children[2]).children.length; i++) {
          methodName += ((SimpleNode) ((ASTArgumentCall) children[2]).children[i]).analyzeType(table);
          if (i != ((ASTArgumentCall) children[2]).children.length - 1)
            methodName += ",";
        }
      }
      methodName += ")";
      if (Parser.getInstance().getTable(methodName) != null) {

      } else {
        System.out.println("Function call " + methodName + " is not valid");
        Parser.getInstance().addSemanticError();
      }
    }
  }

  public int generateCode(SymbolTable table, PrintWriter print) {

    SymbolTable newTable = Parser.getInstance().getTable(methodName);
    String returnType = newTable.getReturnType();
    if (returnType != null){
      returnType = CodeGenerator.smallTypeFromString(returnType);
    }
    else{
      returnType = "V";
    }
    Boolean isStatic = newTable.getStatic();

    int arguments = 0;
    if (!isStatic) {
      arguments++;
      ((SimpleNode) children[0]).generateCode(table, print);
      if (children[2] instanceof ASTArgumentCall && ((SimpleNode) children[2]).children != null) {
        for (int k = 0; k < ((ASTArgumentCall) children[2]).children.length; k++) {
          arguments++;
          ((SimpleNode) ((ASTArgumentCall) children[2]).children[k]).generateCode(table, print);
        }
      }
      print.print("\tinvokevirtual ");
      if (!((SimpleNode) children[0]).analyzeType(table).equals("this")){
        print.print(((SimpleNode) children[0]).analyzeType(table) + ".");
      }
      print.print(((ASTIdentifier) children[1]).getName() + "(");
      if (((SimpleNode) children[2]).children != null) {

        for (int k = 0; k < ((ASTArgumentCall) children[2]).children.length; k++) {
          String type = CodeGenerator
              .smallTypeFromString(((SimpleNode) ((ASTArgumentCall) children[2]).children[k]).analyzeType(table));
          print.print(type);
        }
      }
      print.print(")");

        print.println(returnType);
    } else {
      if (children[2] instanceof ASTArgumentCall && ((SimpleNode) children[2]).children != null) {
        for (int k = 0; k < ((ASTArgumentCall) children[2]).children.length; k++) {
          arguments++;
          ((SimpleNode) ((ASTArgumentCall) children[2]).children[k]).generateCode(table, print);
        }
      }
      if (((SimpleNode) children[0]).analyzeType(table).equals("io")) {
        print.print("invokestatic io/" + ((ASTIdentifier) children[1]).getName() + "(");
      } else {
        print.print("\tinvokestatic " + ((SimpleNode) children[0]).analyzeType(table) + ".");
        print.print(((ASTIdentifier) children[1]).getName() + "(");
      }
      if (((SimpleNode) children[2]).children != null) {

        for (int k = 0; k < ((ASTArgumentCall) children[2]).children.length; k++) {
          String type = CodeGenerator
              .smallTypeFromString(((SimpleNode) ((ASTArgumentCall) children[2]).children[k]).analyzeType(table));
          print.print(type);
        }
      }
      print.print(")");
      print.println(returnType);

    }
    return Math.max(arguments, 1);
  }

}
/*
 * JavaCC - OriginalChecksum=e32282036d533f98d2bbaf06ba78b738 (do not edit this
 * line)
 */
