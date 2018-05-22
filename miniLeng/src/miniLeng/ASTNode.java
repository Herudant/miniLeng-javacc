package miniLeng;

import miniLeng.Simbolo.TipoOperador;

public class ASTNode {


	TipoNodo	type;
	TipoOperador typeOp;
	String		name;
	int			value;
	ASTNode		cond;	// Solo se usa en los tipos WHILE e IF
	ASTNode		left;
	ASTNode		right;  
	ASTNode		next;  // Indica la siguiente instruccion
	
	public enum TipoNodo {
	   DSG , ACC, WHILE, IF, OP, ASG, RD, RW, VAR
	}
	
	
	public ASTNode(TipoNodo type, String name, int value, ASTNode cond, ASTNode left, ASTNode right) {
		this.type = type;
		this.typeOp = null;
		this.name = name;
		this.value = value;
		this.cond = cond;
		this.left = left;
		this.right = right;
		this.next = null;
	}
	
	public ASTNode(String name, TipoNodo type) {
		this.type = type;
		this.typeOp = null;
		this.name = name;
		this.value = 0;
		this.cond = null;
		this.left = null;
		this.right = null;	
		this.next = null;
	}
	
	public ASTNode(String name) {
		this.type = null;
		this.typeOp = null;
		this.name = name;
		this.value = 0;
		this.cond = null;
		this.left = null;
		this.right = null;	
		this.next = null;
	}
	
	public ASTNode(int value) {
		this.type = null;
		this.typeOp = null;
		this.name = "";
		this.value = value;
		this.cond = null;
		this.left = null;
		this.right = null;
		this.next = null;
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


	
}
