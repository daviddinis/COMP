import java.util.*;


public class SymbolTable {

    private LinkedHashMap<String, Symbol> table = new LinkedHashMap<String, Symbol>();
    private SymbolTable parent;
    private String returnType;
    private Boolean isStatic;

    public SymbolTable(SymbolTable parent){
        this.parent = parent;
        this.isStatic = false;
    }
    public SymbolTable(){
        this.parent = null;
        this.isStatic = false;
    }

    public Boolean getStatic(){
        return isStatic;
    }

    public void setStatic(Boolean bool){
        this.isStatic = bool;
    }

    public String getReturnType(){
        return returnType;
    }

    public void setReturnType(String type){
        this.returnType = type;
    }

    public void addSymbol(Symbol symbol){
        table.put(symbol.getId(), symbol);
    }

    public Symbol getSymbol(String id){
        if(table.containsKey(id)){
            return table.get(id);
        }else if (this.parent != null){
            return this.parent.getSymbol(id);
        }else{
            return null;
        }
    }

    public String toString(){
        ArrayList<String> keys = new ArrayList<String>(table.keySet());
        String allKeys = new String();
        for (int i = 0; i< keys.size() ; i++){
            allKeys += keys.get(i) + " ";
        }
        return allKeys;
    }

    public int getTablePosition(String id){
        ArrayList<String> keys = new ArrayList<String>(table.keySet());
        return (keys.indexOf(id) + 1);
    }

}