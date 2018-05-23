package miniLeng;

public class AST {
	
	ASTNode 	root;
	ASTNode		last_inst;
	
	
	public AST() {
		root = null;
		last_inst = null;
	}
	public void crearAST(String name) {
		this.root = new ASTNode(name, ASTNode.TipoNodo.DSG);
		this.last_inst = root;
	}
	
	public ASTNode insertar(String name, ASTNode.TipoNodo type) {
		ASTNode new_ins = new ASTNode(name, type);
		this.last_inst.setNext(new_ins);
		this.last_inst = new_ins;
		
		return new_ins;
	}
	
	public void insertar(ASTNode nodo) {
		last_inst = nodo;
		while (last_inst != null) {
			last_inst = last_inst.getNext();
		}
	}
}
