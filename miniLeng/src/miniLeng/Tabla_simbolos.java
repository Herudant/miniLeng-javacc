package miniLeng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import miniLeng.Simbolo.ClaseParametro;
import miniLeng.Simbolo.TipoSimbolo;
import miniLeng.Simbolo.TipoVariable;

public class Tabla_simbolos {
	
	private final static int TABLE_SIZE = 5;  // Mayor número primo que 2^5 (31)
	private int T[];
	
	ArrayList<LinkedList<Simbolo>> table;
	
	
	/*************************************************************************/
	/************************* Funciones privadas ****************************/
	/*************************************************************************/	
	
	/* Funcion de hash de Pearson, utiliza una tabla auxiliar con los indices
	 * ordenados aleatoriamente. */
	private int hash_function(String s) {
		int h = 0;
		for (int i = 0; i < s.length() ; i++) {
			h = T[(h ^ s.charAt(i)) % TABLE_SIZE];
		}
		return h;
	}
	
	/* Mezcla un array de manera aleatoria */
	private static void shuffleArray(int[] ar)
	  {
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	/* Añade un simbolo en la tabla de simbolos */
	private void addLinkedList(String nombre, Simbolo s) {
		int h = hash_function(nombre);
		LinkedList<Simbolo> lista = table.get(h);
		if (lista == null) {
			lista = new LinkedList<Simbolo>();
			lista.add(s);
			table.add(h, lista);
		}
		else {
			lista.add(s);
		}
	}
	
	/*************************************************************************/
	/************************* Funciones publicas ****************************/
	/*************************************************************************/

	/* Constructor de la clase */
	public Tabla_simbolos() {
		this.table = new ArrayList<LinkedList<Simbolo>>(TABLE_SIZE);
		this.T = new int[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			this.table.add(new LinkedList<Simbolo>());
			T[i] = i;
		}
		shuffleArray(T);
	}
	
	/**********************************************************************
	** Crea una tabla de símbolos vacía.  Este procedimiento debe invocarse 
	** antes de hacer ninguna operación con la tabla de símbolos.
	**********************************************************************/
	public void inicializar_tabla(){
		this.table = new ArrayList<LinkedList<Simbolo>>(TABLE_SIZE);
		this.T = new int[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			this.table.add(new LinkedList<Simbolo>());
			T[i] = i;
		}
		shuffleArray(T);
	}

	
	/**********************************************************************
	** Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida 	con el 
	** del parámetro (se distinguen minúsculas y mayúsculas). Si existe, 
	** devuelve un puntero como resultado, de lo contrario devuelve	NULL
	** o lanza una excepción (se deja a vuestra elección un mecanismo u otro).
	**********************************************************************/
	public Simbolo buscar_simbolo(String nombre)   {  //throws SimboloNoEncontradoException
		Simbolo ret = null;
		int h = hash_function(nombre);
		if(table.get(h) != null) {
			int max_nivel = -1;
			// Busco en la lista enlazada el simbolo de mayor nivel 
			for (Simbolo s : table.get(h)) {
				if (s.getNombre().equals(nombre) && s.getNivel() > max_nivel) {
					max_nivel = s.getNivel();
					ret = s;
				}
			}
		}
		return ret;
	}

	/* Busca el simbolo que coincida con el nombre y nivel de los parámetros */
	public Simbolo buscar_simbolo(String nombre, int nivel)   {  //throws SimboloNoEncontradoException
		Simbolo ret = null;
		int h = hash_function(nombre);
		if(table.get(h) != null) {
			// Busco en la lista enlazada el simbolo de mayor nivel 
			for(int i=nivel; i >= 0 ; i--) {
				for (Simbolo s : table.get(h)) {
					if (s.getNombre().equals(nombre) && s.getNivel() == i) {
						ret = s;
						break;
					}
				}
			}
		}
		return ret;
	}
	
	/****************************************	******************************
	** Introduce en la tabla un simbolo PROGRAMA, con el nombre del parametro, 
	** de nivel 0, con la direccion del parametro. Dado que debe ser el primer
	** simbolo a introducir, NO SE VERIFICA QUE EL SIMBOLO YA EXISTA.
	**********************************************************************/
	public void introducir_programa(String nombre, int dir){
		
		Simbolo s = new Simbolo(TipoSimbolo.PROGRAMA,  		// TipoSimbolo
								null,						// TipoVariable
								null,   						// ClaseParametro
								nombre,				 		// Nombre
								0,							// Nivel
								dir	);						// Dir
								
		
		
		addLinkedList(nombre, s);

	}
	
	
	
	/**********************************************************************
	** Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre, 
	** devuelve NULL (o una excepción, esto se deja a vuestra elección). De lo 
	** contrario, introduce un símbolo VARIABLE (simple) con los datos de los 
	** argumentos.
	**********************************************************************/
	public Simbolo introducir_variable(String nombre, 
										TipoVariable variable, 
										int nivel, int dir)
	{
		
		Simbolo esta = buscar_simbolo(nombre, nivel);
		if (esta != null) 
			return null;	
		else {
			// No está en la lista y lo añado
			Simbolo s = new Simbolo(TipoSimbolo.VARIABLE,  	// TipoSimbolo
									variable,			  	// TipoVariable
									null,   					// ClaseParametro
									nombre,				 	// Nombre
									nivel,					// Nivel
									dir	);					// Dir
			addLinkedList(nombre, s);
			return s;
		}
	}

	
	/**********************************************************************
	** Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre,
	** devuelve NULL. De lo contrario, introduce un símbolo ACCION con los datos
	** de los argumentos.
	**********************************************************************/
	public Simbolo introducir_accion (String 	nombre, 	int nivel, int dir){
		
		Simbolo esta = buscar_simbolo(nombre, nivel);
		if (esta != null) { 
			return null;
		}
		else {
			// No está en la lista y lo añado
			Simbolo s = new Simbolo(TipoSimbolo.ACCION,  		// TipoSimbolo
									null,			  	    // TipoVariable
									null,   					// ClaseParametro
									nombre,				 	// Nombre
									nivel,					// Nivel
									dir	);					// Dir
			addLinkedList(nombre, s);
			return s;
		}
		
	}

	
	/**********************************************************************
	** Si existe un símbolo en la tabla del mismo nivel y con el mismo nombre, 
	** devuelve NULL.  De lo contrario, introduce un símbolo PARAMETRO con los 
	** datos de los argumentos. 
	**********************************************************************/
	public Simbolo introducir_parametro (String nombre, 
										TipoVariable variable,
										ClaseParametro	parametro, 
										int nivel, int dir) 
	{
		Simbolo esta = buscar_simbolo(nombre, nivel);
		if (esta != null) { 
			return null;
		}
		else {
			// No está en la lista y lo añado
			Simbolo s = new Simbolo(TipoSimbolo.PARAMETRO,  	// TipoSimbolo
									variable,			  	// TipoVariable
									parametro,   			// ClaseParametro
									nombre,				 	// Nombre
									nivel,					// Nivel
									dir	);					// Dir
			addLinkedList(nombre, s);
			return s;
		}
	}

	
	/**********************************************************************
	** Elimina de la tabla todos los símbolos PROGRAMA de nivel 0 (debería 
	** haber uno solo).
	**********************************************************************/
	public void eliminar_programa()	{
		for (LinkedList<Simbolo> ls : table) {
			for (Simbolo s : ls) {
				if(s.esPrograma() && s.getNivel() == 0) {
					ls.remove(s);
				}	
			}
		}
	}

	
	/**********************************************************************
	** Elimina de la tabla todas las variables que sean	del nivel del argumento. 
	** NO ELIMINA PARÁMETROS.
	**********************************************************************/
	public void eliminar_variables(int nivel) {
		for (LinkedList<Simbolo> ls : table) {
			if (ls != null) {
				for ( Simbolo s : ls) {
					if(s.esVariable() && s.getNivel() == nivel) {
						ls.remove(s);
					}	
				}
			}
		}
	}

	
	/**********************************************************************
	** Marca todos los parámetros de un nivel como ocultos para que no puedan
	** ser encontrados, pero se mantenga la definición completa de la acción donde
	** están declarados para verificación de invocaciones a la acción.
	**********************************************************************/
	public void ocultar_parametros(int nivel) 	{
		for (LinkedList<Simbolo> ls : table) {
			for ( Simbolo s : ls) {
				if(s.esParametro() && s.getNivel() == nivel) {
					s.setVisible(false);
				}	
			}
		}
	}

	
	/**********************************************************************
	** Elimina de la tabla todas los parámetros que hayan sido ocultados
	** previamente.  LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
	** DEBEN SER ELIMINAODS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	**********************************************************************/
	public void eliminar_parametros_ocultos(int 	nivel)	{
		// TODO:LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS....
		for (LinkedList<Simbolo> ls : table) {
			for ( Simbolo s : ls) {
				if(s.esParametro() && s.getNivel() == nivel && !s.esVisible()) {
					ls.remove(s);
				}	
			}
		}
	}


	/**********************************************************************
	** Elimina de la tabla todas los procedimientos de un nivel.	LOS PARAMETROS 
	** DE ESTAS ACCIONES DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA 
	** COHERENCIA DE LA TABLA.
	**********************************************************************/
	public void eliminar_acciones(int nivel)	{
		// TODO:LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS....
		for (LinkedList<Simbolo> ls : table) {
			for ( Simbolo s : ls) {
				if(s.esAccion() && s.getNivel() == nivel) {
					ls.remove(s);
				}	
			}
		}
	}

}
