import java.util.*;

import java.io.*;

public class CodeGenerator {

    private SimpleNode root;
    private SymbolTable symbolTable;
    private PrintWriter print;
    private String className;
    private SimpleNode classDeclaration;

    public CodeGenerator(SimpleNode root, SymbolTable symbolTable) {
        this.root = root;
        this.symbolTable = symbolTable;
        this.className = Parser.getInstance().className;

        this.classDeclaration = (SimpleNode) this.root.children[this.root.children.length - 1];

        makeFile(className + ".j");
    }

    public void generate() {

        int i = 0;
        while (this.classDeclaration.children.length > i) {
            SimpleNode node = ((SimpleNode) this.classDeclaration.children[i]);
            if (node instanceof ASTImport) {
                createImport(node);
            } else if (node instanceof ASTIdentifier) {
                createNotExtended();
            } else if (node instanceof ASTExtends) {
                createExtend(node);
            } else if (node instanceof ASTVariable) {
                createField(node);
            } else if (node instanceof ASTMainMethod) {
                createMain(node);
            } else if (node instanceof ASTMethod) {
                createMethod(node);
            }

            i++;
        }
        newLine();
        createConstructor();
        this.print.close();
    }

    private void createImport(SimpleNode importNode) {
        // TODO:
    }

    private void createConstructor() {
        this.print.println(".method public <init>()V");
        this.print.println("  aload_0");
        this.print.println("  invokenonvirtual java/lang/Object/<init>()V");
        this.print.println("  return");
        this.print.println(".end method");
        newLine();
    }

    private void createNotExtended() {
        this.print.println(".class public " + this.className);
        this.print.println(".super java/lang/Object");
        newLine();

    }

    private void createExtend(SimpleNode extend) {
        this.print.println(".class public " + this.className);
        this.print.println(".super " + ((ASTIdentifier) extend.children[1]).getName());
        newLine();

    }

    private void createField(SimpleNode field) {

        String type = ((ASTType) field.children[0]).getType();
        String name = ((ASTIdentifier) field.children[1]).getName();
        tab();
        this.print.print(".field public ");
        this.print.println(name + " " + smallTypeFromString(type));
    }

    private void createMain(SimpleNode node) {

        int localSize = 1 + node.getSymbolTable().getSize();

        newLine();
        this.print.println(".method public static main([Ljava/lang/String;)V");
        this.print.println("  .limit locals " + localSize);
        
        int maxStackSize = 0;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            
            if (((SimpleNode) node.jjtGetChild(i)) instanceof ASTStatementDeclarations) {
                
                for (int j = 0; j < ((SimpleNode) node.jjtGetChild(i)).jjtGetNumChildren(); j++) {
                    int stackSize = ((SimpleNode) node.jjtGetChild(i).jjtGetChild(j)).generateCode(node.getSymbolTable(), this.print);
                    maxStackSize = Math.max(stackSize, maxStackSize);
                }
            }
            
        }

        
        this.print.println("\treturn");
        this.print.println("  .limit stack " + maxStackSize);
        this.print.println(".end method");
    }

    private void createMethod(SimpleNode node) {

        newLine();
        String funcName = ((ASTIdentifier) node.children[1]).getName();
        String argTypes = "";

        int arguments = 0;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {

            if (node.jjtGetChild(i) instanceof ASTArgument) {
                arguments++;
                String type = ((ASTType) ((SimpleNode) node.children[i]).children[0]).getType();
                argTypes += smallTypeFromString(type);

            }

        }

        String returnType = smallTypeFromString(((ASTType) node.children[0]).getType());

        this.print.println(".method public " + funcName + "(" + argTypes + ")" + returnType);

        int localSize = 1 + arguments + node.getSymbolTable().getSize();

        this.print.println("  .limit locals " + localSize);
        
        int maxStackSize = 0;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            
            if (((SimpleNode) node.jjtGetChild(i)) instanceof ASTStatementDeclarations) {
                
                for (int j = 0; j < ((SimpleNode) node.jjtGetChild(i)).jjtGetNumChildren(); j++) {
                    int stackSize = ((SimpleNode) node.jjtGetChild(i).jjtGetChild(j)).generateCode(node.getSymbolTable(), this.print);
                    maxStackSize = Math.max(stackSize, maxStackSize);
                }
            }
            
        }
        
        ((ASTReturn) node.children[node.children.length - 1]).generateCode(node.getSymbolTable(),  this.print);
        this.print.println("  .limit stack " + maxStackSize);
        this.print.println(".end method");
    }

    public static String smallTypeFromString(String type) {
        switch (type) {
            case "Int":
                return "I";
            case "Bool":
                return "Z";
            case "Int[]":
                return "[I";
            case "void":
                return "V";
            default:
                return "UNKNOWN TYPE";
        }
    }

    private void tab() {
        this.print.print("\t");
    }

    private void newLine() {
        this.print.print("\n");
    }

    private void makeFile(String outputFile) {

        try {
            File f = new File("jasminFiles/" + outputFile);

            if (!f.exists()) {
                try {
                    f.getParentFile().mkdir();
                } catch (Exception e) {
                }
            }
            this.print = new PrintWriter(f);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}