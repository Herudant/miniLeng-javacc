package miniLeng;

public class Globals {
	public Tabla_simbolos t;
	public AST ast;
	public int nivel;
	public int dir;
	public int erroresSem;
	public int erroresSint;
	public int erroresLex;
	
	public Globals() {
		ast = new AST();
		t = new Tabla_simbolos();
		nivel = 0;
		dir = 0;
		erroresSem = 0;
		erroresSint = 0;
		erroresLex = 0;
	}
	
	public void setDireccionInicial(){
		this.dir = 3;
	}
}
