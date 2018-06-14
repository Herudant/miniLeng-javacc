programa pruebas3;
	entero i,j,k;
	caracter a,b,c;
	booleano foo;

accion a1;
	accion a2;
		accion a3;
	  	principio
  		    escribir("Estoy en a3  ", entacar(10), entacar(13));
  	  	fin
	principio
        escribir("Estoy en a2  ");
  		a3;
  	fin
principio
  	escribir("Estoy en a1  ");
  	a2;
fin
	

accion foo();
principio
	escribir("Estoy en foo  ");
	a1;
fin

accion sumar1(ref entero a);
principio
	a := a + 1;
fin

accion sumarN(ref entero a;  val entero n);
principio
	a := a + n;
fin

principio
	
	foo;
	i := 1;
	escribir("i:", entacar(i), entacar(10), entacar(13));
	sumar1(i);
	escribir("i:", entacar(i), entacar(10), entacar(13));
	sumarN(i,2);
	escribir("i:", entacar(i), entacar(10), entacar(13));

fin