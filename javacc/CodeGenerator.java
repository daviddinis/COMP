import java.util.*;
import java.io.*;

public class CodeGenerator {

    private SimpleNode root;
    private SymbolTable symbolTable;
    private PrintWriter print;
    private String className;

    public CodeGenerator(SimpleNode root, SymbolTable symbolTable) {
        this.root = root;
        this.symbolTable = symbolTable;
        System.out.println(this.root.children.length);
        //this.className = ((SimpleNode)root.children[0].children[0].getValue();
        makeFile(className + ".j" );

                
    }

    public void generate(){
        // creating header for the .j file
        createHeader();


        this.print.close();
    }

    private void createImports(){
        //TODO: 
    }

    private void createHeader() {
        this.print.println(".class public " + this.className); // + nome da classe, etc
    }

    private void makeFile(String outputFile){
        
        try {
            File f = new File(outputFile);

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