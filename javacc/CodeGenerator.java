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
        // creating header for the .j file
        createHeader();

        globalDeclarations();

        this.print.close();
    }

    private void createImports() {
        // TODO:
    }

    private void createHeader() {
        this.print.println(".class public " + this.className);
        SimpleNode extend = ((SimpleNode) ((SimpleNode) root.children[0]).children[0]);

        if (extend instanceof ASTExtends) {
            this.print.println(".super " + ((ASTIdentifier) extend.children[1]).getName());
        } else {
            this.print.println(".super java/lang/Object");
        }
    }

    private void globalDeclarations() {
        newLine();
        int i = 1;
        while (((SimpleNode) this.classDeclaration.children[i]) instanceof ASTVariable) {
            String type = ((ASTType) ((SimpleNode) this.classDeclaration.children[i]).children[0]).getType();
            String name = ((ASTIdentifier) ((SimpleNode) this.classDeclaration.children[i]).children[1]).getName();
            tab();
            this.print.print(".field public ");
            switch (type) {

                case "Int":
                    this.print.println(name + " I");
                    break;
                case "Bool":
                    this.print.println(name + " B");
                    break;
                case "Int[]":
                    this.print.println(name + " [I");
                    break;
            }

            i++;
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