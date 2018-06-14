package miniLeng;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Globals {
	public Tabla_simbolos t;
	public String nombre_prog;
	public int nivel;
	public int dir;
	public int OSF_s;
	public int erroresSem;
	public int erroresSint;
	public int erroresLex;
	public GeneradorCodigo codigo;
	
	public Globals() {
		t = new Tabla_simbolos();
		nivel = 0;
		dir = 0;
		erroresSem = 0;
		erroresSint = 0;
		erroresLex = 0;
		OSF_s = 0;
		nombre_prog = "";
	}
	
	public void setDireccionInicial(){
		this.dir = 3;
	}
	
	public void generarCodigoPrograma(String name) throws FileNotFoundException, UnsupportedEncodingException {
		codigo = new GeneradorCodigo(name);
	}
}
