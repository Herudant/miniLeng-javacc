package miniLeng;

import miniLeng.Simbolo.TipoOperador;

public class ASTNode {


	TipoNodo	type;
	TipoOperador typeOp;
	String		name;
	int			value;
	int			dir;
	ASTNode		cond;	// Solo se usa en los tipos WHILE e IF
	ASTNode		left;
	ASTNode		right;  
	ASTNode		next;  // Indica la siguiente instruccion
	
	public enum TipoNodo {
	   DSG , ACC, WHILE, IF, OP, ASG, RD, RW, VAR, COND, CONST
	}
	
	
	public ASTNode(TipoNodo type, String name, int value, ASTNode cond, ASTNode left, ASTNode right) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
		this.cond = cond;
		this.left = left;
		this.right = right;
	}
	
	public ASTNode(String name, TipoNodo type) {
		super();
		this.type = type;
		this.name = name;
	}
	
	public ASTNode(int value, TipoNodo type) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public ASTNode(String name) {
		super();
		this.name = name;
	}
	
	public ASTNode(String name, int dir) {
		super();
		this.name = name;
		this.dir = dir;
	}
	
	public ASTNode(int value) {
		super();
		this.value = value;
	}
	
	public ASTNode() {
		this.type = null;
		this.typeOp = null;
		this.name = "";
		this.value = 0;
		this.cond = null;
		this.left = null;
		this.right = null;	
		this.next = null;
		this.dir = 0;
	}
	
	public TipoOperador getTypeOp() {
		return typeOp;
	}

	public void setTypeOp(TipoOperador typeOp) {
		this.typeOp = typeOp;
	}

	public TipoNodo getType() {
		return type;
	}

	public void setType(TipoNodo type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ASTNode getCond() {
		return cond;
	}

	public void setCond(ASTNode cond) {
		this.cond = cond;
	}

	public ASTNode getLeft() {
		return left;
	}

	public void setLeft(ASTNode left) {
		this.left = left;
	}

	public ASTNode getRight() {
		return right;
	}

	public void setRight(ASTNode right) {
		this.right = right;
	}
	
	public ASTNode getNext() {
		return next;
	}

	public void setNext(ASTNode next) {
		this.next = next;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	

	
}
