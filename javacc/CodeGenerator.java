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
                createConstructor();
            } else if (node instanceof ASTExtends) {
                createExtend(node);
                createConstructor();
            } else if (node instanceof ASTVariable) {
                createField(node);
            } else if (node instanceof ASTMainMethod) {
                createMain(node);
            } else if (node instanceof ASTMethod) {
                createMethod(node);
            }

            i++;
        }

        this.print.close();
    }

    private void createImport(SimpleNode importNode) {
        // TODO:
    }

    private void createConstructor(){
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
        this.print.println(name + " "+  smallTypeFromString(type));
    }

    private void createMain(SimpleNode node) {
        newLine();
        this.print.println(".method public static main([Ljava/lang/String;)V");
        this.print.println("  .limit stack 99");
        this.print.println("  .limit locals 99");


        this.print.println(".end method");
    }

    private void createMethod(SimpleNode node) {
        newLine();
        String funcName = ((ASTIdentifier) node.children[1]).getName();
        String argTypes = "";
        int i = 2;
        while (node.children[i] instanceof ASTArgument) {
            String type = ((ASTType)((SimpleNode) node.children[i]).children[0]).getType();
            argTypes += smallTypeFromString(type);
            i++;
        }
        String returnType = smallTypeFromString(((ASTType) node.children[0]).getType());

        this.print.println(".method public static " + funcName + "("+ argTypes + ")" + returnType);

        this.print.println("  .limit stack 99");
        this.print.println("  .limit locals 99");

        this.print.println("continhas");


        this.print.println(".end method");
    }

    private String smallTypeFromString(String type){
        switch (type) {
            case "Int":
                return "I";
            case "Bool":
                return "B";
            case "Int[]":
                return "[I";
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