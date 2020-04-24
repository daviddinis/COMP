import java.util.*;


public class SymbolTable {

    private HashMap<String, Symbol> table = new HashMap<String, Symbol>();
    private SymbolTable parent;

    public SymbolTable(SymbolTable parent){
        this.parent = parent;
    }
    public SymbolTable(){
        this.parent = null;
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
}