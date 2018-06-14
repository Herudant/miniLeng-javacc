Programa p;
entero i, j, m, a1;
booleano fallo, semos, hambre;

accion a1 (REF ENTERO a);							% a1 repetida
Principio
	a:=b;											% b no definido
Fin
accion a2 (val entero b);
	booleano m;
	accion a3(ref entero n,n,pez);					% parametros repetidos
	Principio
		n:=n+1;
	Fin
	accion a1;
	Principio
		a1:=a1+1;									% a1 no es una variable
		caca(j);									% accion no definida 1, variable no definida
		caca(j);									% accion no definida 2, variable no definida
		fallo:="hola" = "hola";						% no se permiten cadenas 1
		fallo:="a">="c";
		fallo:="hola">2;							% incompatibilidad de tipos en expresion
		fallo:=1>3;									
	Fin
Principio
	a3(b);											% no se puede pasar un parametro val por ref 1, faltan parametos en la invoacion 1
Fin
%-----------------------------------------------------------
accion dato (ref entero d);
%-----------------------------------------------------------
entero num;
Principio 
		  caca(j);									% accion no definida 3, variable no definida
		  a3(d);									% no es accesible
		  a3(d);									% no es accesible
		  d:=num;	
          d:=0;
		  d:="hola";								% no se permiten cadenas 3, error tipos
		  d:=fallo+3;
		  fallo:=entacar(fallo);					% no se puede convertir un bool a car
		  d:=hombre;								% variable no definida
		  leer(dato);								% variable no definida, debe ser param o var
        Mq d<=0
                escribir("Escribe un numero: ");
                leer(d);
                Si d<=0 ent
                        escribir("El numero debe ser postivo.");
                        escribir (entacar (13), entacar (10));
                FSi
        FMq
		  
Fin

%-----------------------------------------------------------
accion mcd(Val entero  a,b ; ref  entero  m );
%-----------------------------------------------------------
entero r;
Principio
        r:=a mod b;
		  
        Mq r<>0
                a:=b;							% parametro como valor no puede ser aisgnado
                b:=r;							% parametro como valor no puede ser aisgnado
                r:=a mod b;
        FMq
		m := b;
Fin

%-----------------------------------------------------------
Principio
		 a1:=1;	
		  hombre:=1;							% variable no definida hombre
		  dato(hombre);
        dato(i);
        dato(j);
        mcd (i,j,m);
		  mcd(i,j,m*i);
		  ccd(i,j,m);	
		  mcd;									% numero de parametros erroneo
        escribir("El MCD es: ", m);				% no se puede escribir una variable del tipo entero
        escribir (entacar (13), entacar (10));
Fin
