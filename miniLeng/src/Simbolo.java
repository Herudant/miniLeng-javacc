

import java.util.ArrayList;

public class Simbolo {

	/* Atributos de la clase Simbolo */
	Tipo_simbolo 		tipo;
	Tipo_variable 		variable;
	Clase_parametro		parametro;
	ArrayList<Simbolo> 	lista_parametros;
	String	nombre;
	int		nivel;
	boolean 	visible;
	long 	dir;
	
	
	/* Constructores de la clase Simbolo */
	public Simbolo() {}
	public Simbolo(Tipo_simbolo tipo, Tipo_variable variable, Clase_parametro parametro,
					String nombre, int nivel, boolean visible, long dir) {
		this.tipo = tipo;
		this.variable = variable;
		this.parametro = parametro;
		this.nombre = nombre;
		this.nivel = nivel;
		this.visible = visible;
		this.dir = dir;
		this.lista_parametros = new ArrayList<Simbolo>();
	}
	
	/* Getters y setters */
	public Tipo_simbolo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_simbolo tipo) {
		this.tipo = tipo;
	}
	public Tipo_variable getVariable() {
		return variable;
	}
	public void setVariable(Tipo_variable variable) {
		this.variable = variable;
	}
	public Clase_parametro getParametro() {
		return parametro;
	}
	public void setParametro(Clase_parametro parametro) {
		this.parametro = parametro;
	}
	public ArrayList<Simbolo> getLista_simbolos() {
		return lista_parametros;
	}
	public void setLista_simbolos(ArrayList<Simbolo> lista_parametros) {
		this.lista_parametros = lista_parametros;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public long getDir() {
		return dir;
	}
	public void setDir(long dir) {
		this.dir = dir;
	}

	
	/* Tipos enumerdado para atributos */
	public enum Tipo_simbolo {
	    PROGRAMA, VARIABLE, ACCION, PARAMETRO
	}
	
	public enum Tipo_variable {
	    DESCONOCIDO, ENTERO, BOOLEANO, CHAR, CADENA
	}
	public enum Clase_parametro {
	    VAL, REF
	}
	
	/* Funciones publicas */
	public void introducir_parametro(String nombre, Tipo_variable tipo_var, Clase_parametro param, int nivel) {
		this.nombre = nombre;
		this.variable = tipo_var;
		this.parametro = param;
		this.nivel = nivel;
	}
	
	public boolean esVariable() {
		return this.tipo == Tipo_simbolo.VARIABLE;
	}
	public boolean esParametro() {
		return this.tipo == Tipo_simbolo.PARAMETRO;
	}
	public boolean esAccion() {
		return this.tipo == Tipo_simbolo.ACCION;
	}
	public boolean esValor() {
		return this.parametro == Clase_parametro.VAL;
	}
	public boolean esReferencia() {
		return this.parametro == Clase_parametro.REF;
	}
}
