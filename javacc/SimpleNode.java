

/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 6.1 */
/* JavaCCOptions:MULTI=false,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected Parser parser;
  protected String name = null;
  protected int line;
  protected SymbolTable table;


  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(Parser p, int i) {
    this(i);
    parser = p;
  }


  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() {
    return ParserTreeConstants.jjtNodeName[id];
  }


  public String toString(String prefix) {
    if (this.value == null){
    return prefix + toString();
     }
     else {
       return prefix + toString() + " " + jjtGetValue();
     }
  }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = ((SimpleNode)children[i]);
        if (n != null) {
          n.dump(prefix + "   ");
        }
      }
    }
  }

  public int getId() {
    return id;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int l) {
    line = l;
  }

  public void createSymbolTable(SymbolTable symbolTable){
    if (children == null) return;

    for (int i = 0; i < children.length; i++){
      ((SimpleNode) children[i]).createSymbolTable(symbolTable);
    }
  }
  
  public void analyzeSemantics(SymbolTable symbolTable){
    if (children == null) return;

    for (int i = 0; i < children.length; i++){
      ((SimpleNode) children[i]).analyzeSemantics(symbolTable);
    }
  }

  public String analyzeType(SymbolTable table) {
    return "";
  }

  public boolean isInitialized(SymbolTable table) {
    return false;
  }
  
  public void initialize(SymbolTable table) {
    
  }
}

/* JavaCC - OriginalChecksum=c6a53a7e2b2b8ce930bd6fa4f9a48784 (do not edit this line) */
