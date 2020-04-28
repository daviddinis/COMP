import java.util.*;
import java.io.*;

public class CodeGenerator{

    private SimpleNode root;
    private SymbolTable symbolTable;
    private PrintWriter print;

    public CodeGenerator(SimpleNode root, SymbolTable symbolTable, String codeFile){
        this.root = (SimpleNode)root.jjtGetChild(0);
        this.symbolTable = symbolTable;

        //creating the file
        try{
			File f = new File(codeFile);

			if(!f.exists())
			    try {
                    f.getParentFile().mkdir();
                } catch (Exception e) {}

			this.print = new PrintWriter(f);

		} catch(IOException exception){

			exception.printStackTrace();
		}

        //creating header for the .j file
        createHeader();


        this.print.close();
    }

    private void createHeader(){
        this.print.println(".class public "); //+ nome da classe, etc
    }
    
}