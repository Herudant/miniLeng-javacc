programa pruebas2;
	entero i,j,k;
	caracter a,b,c;
	booleano foo,bar;
	
principio
	i := 2 * 3 + 1;
	j := i * 2 + 4 - 6 div 3;
	k := ( 3 * i ) + (2-j);
	escribir("i:", entacar(i), "  j:", entacar(j),  "  k:", entacar(k), entacar(10), entacar(13));

	a := "a";
	b := "b";
	c := "a";

	foo := a = b;
	bar := a = c;
	escribir("a:", a, "  b:", b, "  c:", entacar(10), entacar(13));
	escribir("j > k?  ");
	
	Si (j > ( 3 * i ) + (2-j)) ent
		escribir ("Cierto", entacar(10), entacar(13));
	si_no
		escribir ("Falso", entacar(10), entacar(13));
	fsi

	si ( 3 > 2) ent
		escribir("Siempre entro aqui");
		si ( i*0 < 2) ent
			escribir("    Tambien entro aqui");
		fsi
	si_no
		escribir("Nunca entro aqui");
	fsi

	i := 0;
	Mq (i < 5) 
		escribir("i:", entacar(i), entacar(10), entacar(13));
		i := i+1;
	Fmq

fin