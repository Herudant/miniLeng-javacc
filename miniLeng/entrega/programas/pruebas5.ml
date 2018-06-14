programa pruebas5;
	entero i,j,k;
	caracter a,b,c;
	booleano foo,bar;


accion a1;
	accion a2;
	principio
        escribir("Estoy en a2  ");
  		a3;
  	fin
principio
  	escribir("Estoy en a1  ");
  	a2;
fin
	
accion foo2(ref entero a1);
principio
	escribir("Error a1 es una accion");
fin


accion sumar1(ref entero a);
principio
	a := a + 1;
fin

accion sumarN(ref entero a;  val entero n);
principio
	a := a + n;
fin

accion foo3(val entero asd);
principio
	escribir("Error a1 es una accion");
	asd := 3+2;		    % val no puede ser asignado
	sumar1(asd);		% val no se puede pasar como ref
fin

principio

	a := "abc";			% error cadena
	b := "b" = "b";		% error tipos
	c := "a" + 3;		% error tipos

	i := 3 + pepe;		% id no encontrado
	i := 3 + pepe;		% no da error al ser añadido como DESCONOCIDO

	foo := a = b;
	bar := a = c;
	escribir("a:", a, "  b:", b, "  c:", entacar(10), entacar(13));
	escribir("j > k?  ");

	si ( 3 > 2) ent
		escribir("Siempre entro aqui");
		si ( i* 4 + 2) ent   % el operador debe ser booleano
			escribir("    ERROR!");
		fsi
	si_no
		escribir("Nunca entro aqui");
	fsi

	a2;					% no es visible
	sumar1(a);			% incompatibilidad de tipos
	sumarN(i);			% num parametros incorrectos
	sumarN(i,i,i);		


fin