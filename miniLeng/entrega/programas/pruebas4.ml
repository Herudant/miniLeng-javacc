programa pruebas4;
	entero i,j,k;
	caracter a,b,c, ln;
	booleano foo,bar;
	
principio
    escribir("i: ");
	leer(i);
	escribir(entacar(10), entacar(13));
	escribir("i:", entacar(i), entacar(10), entacar(13));

	escribir("j k:");
	leer(j,k);
	escribir(entacar(10), entacar(13));
	escribir("j:", entacar(j), "  k:", entacar(k),entacar(10), entacar(13));

	b := entacar(i);
	escribir("b=i: ", b, entacar(10), entacar(13));

	si (b = entacar(i)) ent
		escribir("correcto", entacar(10), entacar(13));
	fsi


	a := " ";
	escribir("a:");
	leer(a, ln);
	escribir(entacar(10), entacar(13));
	escribir("a:  ", a);

	k := caraent(a);

	escribir("k=a: ", k, entacar(10), entacar(13));

	si (k = caraent(a)) ent
		escribir("correcto", entacar(10), entacar(13));
	fsi

fin