# [Compiladores 2025-1] Grupo 7008 
## Práctica 3 Repositorio base


### Estructura del directorio
```c++
P3
├── README.md
├── src
│   └── main
│       ├── java
│       │   ├── ClaseLexica.java // Clase que reemplaza el enum de P2
│       │   ├── Main.java // Clase con el método main
│       │   ├── Parser.java // Implementación del An. Sintáctico
│       │   ├── ParserInterface.java // interfaz de an. sintácticos de descenso recursivo
│       │   └── Token.java // Clase para componentes léxicos
│       └── jflex
│           └── Lexer.flex // Definición del An. Léxico
└── tst
    └── prueba.txt // Archivo de entrada prueba que debe ser aceptado por el parser

```

### Uso

#### Compilacion

```bash
$ jflex src/main/jflex/Lexer.flex
$ javac --source-path src -d build src/main/jflex/Main.java
```

#### Ejecucion

```bash
$ java -cp build main.java.Main tst/prueba.txt  
```

#### Ejercicios
Para la gramática G = ( N, Σ, P, S), descrita por las siguientes producciones: 
> P = {
>> programa → declaraciones sentencias <br>
>> declaraciones → declaraciones declaracion | declaracion <br>
>> declaracion → tipo lista-var **;** <br>
>> tipo → **int** | **float** <br>
>> lista_var → lista_var **,** _**identificador**_ | _**identificador**_ <br>
>> sentencias → sentencias sentencia | sentencia <br>
>> sentencia → _**identificador**_ **=** expresion **;** | **if** **(** expresion **)** sentencias **else** sentencias | **while** **(** expresión **)** sentencias <br>
>> expresion → expresion **+** expresion | expresion **-** expresion | expresion __\*__ expresion | expresion **/** expresión | _**identificador**_ | **_numero_** <br>
>> expresion → **(** expresion **)** <br>
}


1. Determinar en un archivo Readme, en formato Markdown (.md) o LaTeX (.tex) -- con su respectivo PDF, para este último -- , los conjuntos _N_, _Σ_ y el símbolo inicial _S_.  (0.5 pts.)
2. Mostrar en el archivo el proceso de eliminación de ambigüedad o justificar, en caso de no ser necesario. (1 pts.).
3. Mostrar en el archivo el proceso de eliminación de la recursividad izquierda o justificar, en caso de no ser necesario. (1 pts.)
4. Mostrar en el archivo el proceso de factorización izquierda o justificar, en caso de no ser necesario. (1 pts.)
5. Mostrar en el archivo los nuevos conjuntos _N_ y _P_. (0.5 pts.)
6. Sustituir el enum de la práctica 2 con una definición de clases léxicas utilizando enteros (ClaseLexica.java). (0.5 pts.)
7. Implementar el Analizador Sintáctico (Parser.java) de descenso recursivo, documentando las funciones de cada No-Terminal, de forma que el programa descrito en el archivo _prueba.txt_ sea reconocido y aceptado por el analizador resultante. (4 pts.)
8. Proveer una segunda versión del programa en una implementación de la interfaz ParserInterface.java, en la que los analizadores léxico y sintáctico hagan uso de la clase Token (Token.java). (2 pts.)

---
#### Extras

9. Documentar el código. (0.25pts)
10. Proponer 4 archivos de prueba nuevos, 2 válidos y 2 inválidos. (0.25pts)
