package miniLeng;

import miniLeng.Simbolo.ClaseParametro;
import miniLeng.Simbolo.TipoOperador;
import miniLeng.Simbolo.TipoVariable;

public class ASTNode {


	TipoNodo	type;
	TipoOperador typeOp;
	TipoVariable typeVar;
	ClaseParametro typeParam;
	String		name;
	int			value;
	int			dir;
	int 		nivel;
	boolean		negar_valor;
	ASTNode		cond;	// Solo se usa en los tipos WHILE e IF
	ASTNode		left;
	ASTNode		right;  
	ASTNode		next;  // Indica la siguiente instruccion
	


	public enum TipoNodo {
	   DSG , ACC, WHILE, IF, OP, ASG, FUN, RD, WR, VAR, COND, ENTCAR, CARENT, CONST, INVOCACION
	}
	
	public ASTNode(String name, int nivel, TipoNodo type) {
		super();
		this.nivel = nivel;
		this.type = type;
		this.name = name;
	}
	
	public ASTNode(int value, int nivel, TipoNodo type) {
		super();
		this.nivel = nivel;
		this.type = type;
		this.value = value;
	}
	public ASTNode(TipoNodo type) {
		super();
		this.type = type;
	}
	public ASTNode(String name, int nivel, TipoVariable typeVar) {
		super();
		this.name = name;
		this.nivel = nivel;
		this.type = TipoNodo.VAR;
		this.typeVar = typeVar;
	}
	public ASTNode(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	public ASTNode(String name, int nivel, int dir) {
		super();
		this.nivel = nivel;
		this.name = name;
		this.dir = dir;
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
		this.negar_valor = false;
		this.dir = 0;
		this.nivel = 0;
		this.typeVar = null;
		this.typeParam = null;
	}
	
	
	public ClaseParametro getTypeParam() {
		return typeParam;
	}

	public void setTypeParam(ClaseParametro typeParam) {
		this.typeParam = typeParam;
	}
	
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public TipoVariable getTypeVar() {
		return typeVar;
	}

	public void setTypeVar(TipoVariable typeVar) {
		this.typeVar = typeVar;
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

	public boolean negarValor() {
		return negar_valor;
	}

	public void setNegar_valor(boolean negar_valor) {
		this.negar_valor = negar_valor;
	}

	

	
}
