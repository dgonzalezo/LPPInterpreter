# Intérprete de LPP

Realizado por:

- ###  Juan Camilo García Martínez

- ### Elián González Ordóñez

## Tests
El interprete es capaz de ejecutar los casos 1, 2, 5 y 6 presentados.

# Instrucciones
Se realizó un intérprete capaz de ejecutar todos las instrucciones de LPP, asi como de ejecutar
funciones y procedimientos.


El interprete corre el codigo fuente encontrado en el archivo input/06.in por defecto.

Se puede cambiar el archivo a ejecutar, modificando el argumento tomado por el lexer.


LPPLexer lexer = new LPPLexer(CharStreams.fromFileName("input/06.in"));



# Ejemplo

#### whileEdxample.in

```
entero contador

inicio
contador <- -5

    mientras contador < 10 haga
        escriba "Contador aun no es igual a 10!, contador es ", contador, "!"
        llamar nueva_linea
        contador <- contador + 1
    fin mientras

    ESCRIBA "Contador ahora si vale ", contador
fin
```