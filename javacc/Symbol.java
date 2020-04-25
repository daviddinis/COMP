
public class Symbol {
    private String type;
    private String identifier;
    private boolean isInitialized = false;

    public Symbol(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }


    public String getId(){
        return this.identifier;
    }

    public String getType(){
        return this.type;
    }

    public void initialize() {
        isInitialized = true;
    }

    public boolean isInitialized() {
        return isInitialized;
    }
} 