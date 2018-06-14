programa pruebas1;
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
	escribir("a:", a, "  b:", b, "  c:", entacar(10), entacar(13));

	foo := a = b;
	bar := a = c;
fin
