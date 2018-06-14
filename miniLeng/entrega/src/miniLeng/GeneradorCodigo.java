package miniLeng;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GeneradorCodigo {

	private int num_etiqueta;
	private PrintWriter writer = null;
	private static final String FILEPATH = "/Users/heru/git/miniLeng/miniLeng-javacc/miniLeng/codigo/";
	
	public GeneradorCodigo(String nombre) throws FileNotFoundException, UnsupportedEncodingException {
		num_etiqueta = 0;
		writer = new PrintWriter(FILEPATH + nombre + ".code", "UTF-8");
	}
	
	public void cerrar() {
		writer.close();
	}
	
	public String nueva_etiqueta() {
		return "L" + ++num_etiqueta;
	}
		
	public void escribir(String msg) {
		writer.println(msg);
	}
	
	public void escribir(ASTNode tree) {
		ASTNode instr = tree;
		String label1 = null, label2 = null;
		int f, o;
		while(instr != null) {
			switch (instr.getType()) {
			case ASG:
				/* Pasos:
				 * 			;Direccion variable
				 * 				SRF		x	y
				 * 			;Lectura operandos notacion polaca inversa
				 * 				A	B	+
				 * 				...
				 * 			;Asignacion
				 * 				ASG
				 */
				ASTNode var = instr.getLeft();
				f = instr.getNivel() - var.getNivel();
				o = var.getDir();
				writer.println("; Direccion " + var.getName());
				writer.println("\tSRF\t" + f + "\t" + o);
				if(var.getTypeParam() == Simbolo.ClaseParametro.REF)
					writer.println("\tDRF");
				// Lectura operandos notacion polaca inversa
				this.recorrido_profundidad(instr.getRight(), instr.getNivel(), false);
				
				writer.println("; Asignacion a " + var.getName());
				writer.println("\tASG");
				break;
			case WHILE:
				/* Pasos:
				 * 			Li:
				 * 			; MQ
				 *			; Comprobar condicion (si no se cumple saltar a Li+1)
				 *				...
				 *			; Lista de sentencias
				 *				.... 	
				 *			; Saltar a Li
				 *				JMP Li
				 *			; Fin MQ
				 *			Li+1:
				 */
				label1 = this.nueva_etiqueta();
				writer.println(label1 + ":");
				writer.println("; MQ");
				writer.println("; Comprobar condicion");
				this.recorrido_profundidad(instr.getCond(), instr.getNivel(), false);
				writer.println("; Salir del bucle si se evalua falso");
				label2 = this.nueva_etiqueta();
				writer.println("\tJMF\t"+ label2);
				writer.println("; Lista de sentencias");
				this.escribir(instr.getRight());
				writer.println("; Fin de la iteración. Saltar a la cabecera del bucle.");
				writer.println("; Fin MQ");
				writer.println("\tJMP\t" + label1);
				writer.println(label2 + ":");
				break;
			case IF:
				/* Pasos:
				 * 			; SI
				 *			; Comprobar condicion (si no se cumple saltar a Li)
				 *				...
				 *			; ENT
				 *			; Lista de sentencias
				 *				.... 	
				 *			; Saltar a Li+1 (si hay SI_NO)
				 *				JMP Li+1
				 *			; SI_NO
				 *			Li:
				 *			; Lista_sentencias
				 *			; Fin SI
				 *			Li+1:
				 */	

				label1 = this.nueva_etiqueta();
				writer.println("; SI");
				writer.println("; Comprobar condicion");
				this.recorrido_profundidad(instr.getCond(), instr.getNivel(), false);
				writer.println("\tJMF\t" + label1);
				writer.println("; Lista de sentencias");
				this.escribir(instr.getLeft());
				
				if (instr.getRight() == null) {
					writer.println( label1 + ":");
				}
				else {
					// Si hay SI_NO
					label2 = this.nueva_etiqueta();
					writer.println("\tJMP\t" + label2);
					writer.println("; SI_NO");
					writer.println(label1 + ":");
					this.escribir(instr.getRight());
					writer.println("; Fin SI");
					writer.println( label2 + ":");
				}
				break;
			case RD:
				/* Pasos:
				 * 			; Leer
				 *			; Dirección de variable x
				 *				SRF	x	y
				 *				RD 0|1		; 0 char - else int
				 */
				writer.println("; Leer");
				// Leer dirección de cada variable
				ASTNode variable = instr.getRight();
				while(variable != null) {
					// Operacion RD sobre variable
					String num = (variable.getTypeVar() == Simbolo.TipoVariable.CADENA || variable.getTypeVar() == Simbolo.TipoVariable.CHAR) ? "0" : "1";
					f = instr.getNivel() - variable.getNivel();
					o = variable.getDir();
					writer.println("; direccion " + variable.getName());
					writer.println("\tSRF\t" + f + "\t" + o);
					if(variable.getTypeParam() == Simbolo.ClaseParametro.REF)
						writer.println("\tDRF");
					if(variable.negarValor()) {
						writer.println("\tNGB");
					}
					writer.println("\tRD\t" + num);
					variable = variable.getRight();
				}
				break;
			case WR:
				/* Pasos:
				 * 			; Escribir
				 *			; Dirección de variable 
				 *				SRF	x	y
				 *				DRF
				 *				WRT 0|1		; 0 char - else int
				 */
				// Leer dirección de cada variable
				ASTNode parametro = instr.getRight();
				
				while(parametro != null) {
					writer.println("; Escribir " + parametro.getName());
					if (parametro.getType() == ASTNode.TipoNodo.CONST) {
						// No escribo los "" del principio y fin
						
						if(parametro.getTypeVar() != Simbolo.TipoVariable.ENTERO) {
							for(int i=1; i < parametro.getName().length()-1 ; ++i) {
								writer.println("\tSTC\t" + (int)parametro.getName().charAt(i));
								writer.println("\tWRT\t0");
							}
						}
						else {
							writer.println("\tSTC\t" + parametro.getName());
							writer.println("\tWRT\t0");
						}
						
						if(parametro.negarValor()) {
							writer.println("\tNGB");
						}
						
					}
					else if(parametro.getType() == ASTNode.TipoNodo.ENTCAR) {
						// Ejecutar expresion y escribir como tipo char
						this.recorrido_profundidad(parametro.getLeft(), parametro.getNivel(), false);
						writer.println("\tWRT\t1");
					}
					else {
						// Si es VAR : "SRF nivel dir" ; "DRF" ; WRT
						String num = (parametro.getTypeVar() == Simbolo.TipoVariable.CADENA | parametro.getTypeVar() == Simbolo.TipoVariable.CHAR) ? "0" : "1";
						f = instr.getNivel() - parametro.getNivel();
						o = parametro.getDir();
						writer.println("; direccion " + parametro.getName());
						writer.println("\tSRF\t" + f + "\t" + o);
						writer.println("\tDRF");
						if(parametro.negarValor()) {
							writer.println("\tNGB");
						}
						// Valor de un parametro por referencia
						if(parametro.getTypeParam() == Simbolo.ClaseParametro.REF)
							writer.println("\tDRF");
						writer.println("\tWRT\t" + num);
					}
					parametro = parametro.getRight();
				}
				break;	
			case FUN:
				/* Pasos:	
				 * 			; Accion R
				 * 			; Recuperar argumentos R
				 *			R: 
				 *		
				 *			; codigo de R
				 *
				 *				CSF
				 */			
				writer.println("\n; accion " + instr.getName() + ".");				
				writer.println(";comienzo accion " + instr.getName() + ".");
				this.escribir(instr.getName() + ":"); 
				
				// Recuperar argumentos
				ASTNode param = instr.getLeft();

				while(param != null) {
					f = instr.getNivel() - param.getNivel();
					o = param.getDir();
					writer.println("; direccion " + param.getName());
					writer.println("\tSRF\t" + 0 + "\t" + o);
					writer.println("\tASGI");
					
					param = param.getLeft();
				}
				
				// Codigo de la funcion(bloque_sentencias)
				this.escribir(instr.getRight());
				
				
				// Fin de la accion
				writer.println(";Fin de la acción / función\n\tCSF");
				
				break;
			case INVOCACION:
				/* Pasos:	
				 * 			; Apilar variables
				 * 			; Invocar funcion
				 *				OSF	 s l a
				 */	
				ASTNode accion = instr.getCond();
				f = instr.getNivel() - ((accion == null) ? 0 : accion.getNivel() );
				writer.println("\n; invocacion accion " + instr.getName() + ".");
				this.recorrido_profundidad(instr.getLeft(), instr.getNivel(), true);
				writer.println("\tOSF " + accion.getValue() + "  "  + f + " "+ accion.getName());
				break;
			default:
				break;
			}
			instr = instr.getNext();
		}
	}
	
	private void recorrido_profundidad(ASTNode node, int nivel, boolean inv_func) {
		if (node != null) {
			String msg = new String();
			this.recorrido_profundidad(node.getLeft(), nivel, inv_func);
			this.recorrido_profundidad(node.getRight(), nivel, inv_func);
			switch (node.getType()) {
			case CONST:
				// Si es CONST puede ser 
				//    ENTERO          -  atributo name valdra "" y value contendrá el valor
				//    CADENA/CHAR     -  atributo name contendrá el valor de la cadena
				//    BOOLEANO
				msg = (node.getTypeVar() == Simbolo.TipoVariable.CHAR) ? node.getName() : Integer.toString(node.getValue()) ;
				//msg = (node.name != "") ? Integer.toString(node.getValue()) : node.getName();
				writer.println("\tSTC\t" + msg);
				if(node.negarValor()) {
					writer.println("\tNGB");
				}
				break;
			case VAR:
				// Si es VAR : "SRF nivel dir" y "DRF"
				// inv_func indica que es llamada a funcion, en caso de ser True se mira el tipo del parámetro
				int f = nivel - node.getNivel();
				int o = node.getDir();
				writer.println("; direccion " + node.getName());
				writer.println("\tSRF\t" + f + "\t" + o);
				if (!inv_func) {
					writer.println("\tDRF");		
					// Valor de un parametro por referencia
					if(node.getTypeParam() == Simbolo.ClaseParametro.REF)
						writer.println("\tDRF");
					if(node.negarValor()) {
						writer.println("\tNGB");
					}
				}
				else {
					if (node.getTypeParam() == Simbolo.ClaseParametro.VAL) {
						writer.println("\tDRF");
						if(node.negarValor()) {
							writer.println("\tNGB");
						}
					}
				}
				break;
			case OP:
				// Si es un operador escribimos su nemotecnico
				// writer.println("; operador");
				this.escribir_operador(node);
				break;
			case COND:
				// Si es una condicion escribimos su nemotecnico
				writer.println("; condicion");
				this.escribir_operador(node);
				break;
			default:
				break;
			}
		}
	}
	
	private void escribir_operador(ASTNode node) {
		switch (node.getTypeOp()) {
		case SUMA:
			writer.println("\tPLUS");
			break;
		case RESTA:
			writer.println("\tSBT");
			break;
		case DIV:
			writer.println("\tDIV");
			break;
		case MUL:
			writer.println("\tTMS");
			break;
		case MOD:
			writer.println("\tMOD");
			break;
		case OR:
			writer.println("\tOR");
			break;
		case AND:
			writer.println("\tAND");
			break;
		case LT:
			writer.println("\tLT");
			break;
		case GT:
			writer.println("\tGT");
			break;
		case LE:
			writer.println("\tLTE");
			break;
		case GE:
			writer.println("\tGTE");
			break;
		case EQ:
			writer.println("\tEQ");
			break;
		case NE:
			writer.println("\tNEQ");
			break;
		case NOT:
			writer.println("\tNGB");
			break;
		default:
			break;
		}
	}
}
