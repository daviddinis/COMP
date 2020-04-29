
public class Symbol {

    public static enum Access { global, local, parameter }

    private String type;
    private String identifier;
    private boolean isInitialized = false;
    private boolean scopeInitialized = false;
    private Access access;

    public Symbol(String type, String identifier, Access acc) {
        this.type = type;
        this.identifier = identifier;
        this.access = acc;
        if (this.access == Access.parameter) {
            initialize();
        }
    }

    public String getId(){
        return this.identifier;
    }

    public String getType(){
        return this.type;
    }

    public Access getAccess() {
        return this.access;
    }

    public void initialize() {
        isInitialized = true;
    }

    public void scopeInitialize() {
        scopeInitialized = true;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isScopeInitialized() {
        return scopeInitialized;
    }
} 