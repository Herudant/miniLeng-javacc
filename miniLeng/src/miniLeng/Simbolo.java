package miniLeng;

import java.util.ArrayList;

public class Simbolo {

	/*************************************************************************/
	/****************************** Atributos ********************************/
	/*************************************************************************/
	TipoSimbolo 			tipo;
	TipoVariable 		variable;
	ClaseParametro		parametro;
	ArrayList<Simbolo> 	lista_parametros;
	String				nombre;
	int					nivel;
	boolean 				visible;
	int 				dir;	
	
	/*************************************************************************/
	/**************************** Constructores ******************************/
	/*************************************************************************/
	public Simbolo() {}
	public Simbolo (TipoSimbolo tipo, 
			 		TipoVariable variable, 
			 		ClaseParametro parametro,
					String nombre, 
					int nivel, 
					int dir) 
	{
		this.tipo = tipo;
		this.variable = variable;
		this.parametro = parametro;
		this.nombre = nombre;
		this.nivel = nivel;
		this.visible = true;
		this.dir = dir;
		this.lista_parametros = new ArrayList<Simbolo>();
	}
	
	/*************************************************************************/
	/************************** Getters y Setters ****************************/
	/*************************************************************************/
	public TipoSimbolo getTipo() {
		return tipo;
	}
	public void setTipo(TipoSimbolo tipo) {
		this.tipo = tipo;
	}
	public TipoVariable getVariable() {
		return variable;
	}
	public void setVariable(TipoVariable variable) {
		this.variable = variable;
	}
	public ClaseParametro getParametro() {
		return parametro;
	}
	public void setParametro(ClaseParametro parametro) {
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
	public boolean esVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	/*************************************************************************/
	/********************** Enumerados para atributos ************************/
	/*************************************************************************/
	public enum TipoSimbolo {
	    PROGRAMA, VARIABLE, ACCION, PARAMETRO
	}
	public enum TipoVariable {
	    DESCONOCIDO, ENTERO, BOOLEANO, CHAR, CADENA
	}
	public enum ClaseParametro {
	    VAL, REF
	}
	
	/*************************************************************************/
	/************************* Funciones publicas ****************************/
	/*************************************************************************/
	public void introducir_parametro(String nombre, 
									TipoVariable tipo_var, 
									ClaseParametro param, 
									int nivel) 
	{
		this.nombre = nombre;
		this.variable = tipo_var;
		this.parametro = param;
		this.nivel = nivel;
	}
	public boolean esVariable() {
		return this.tipo == TipoSimbolo.VARIABLE;
	}
	public boolean esParametro() {
		return this.tipo == TipoSimbolo.PARAMETRO;
	}
	public boolean esAccion() {
		return this.tipo == TipoSimbolo.ACCION;
	}
	public boolean esValor() {
		return this.parametro == ClaseParametro.VAL;
	}
	public boolean esReferencia() {
		return this.parametro == ClaseParametro.REF;
	}
	public boolean esPrograma() {
		return this.tipo == TipoSimbolo.PROGRAMA;
	}
	/*************************************************************************/
}