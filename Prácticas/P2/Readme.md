<p  align="center">
  <img  width="200"  src="https://www.fciencias.unam.mx/sites/default/files/logoFC_2.png"  alt="">  <br>Compiladores  2025-1 <br>
  Práctica 2: Analizadores léxicos con Lex (JFlex) <br> Profesora: Ariel Adara Mercado Martínez
</p>

## Analizador léxico para el lenguaje C_1
### Objetivo:
Que el alumno conozca y utilice los principios para generar analizadores léxicos utilizando Lex.

### Introducción
Lex es una herramienta para generar analizadores léxicos, que se deben describir mediante las expresiones regulares de los tokens que serán reconocidas por el analizador léxico (scanner o lexer). Originalmente fue desarrollado para el sistema operativo Unix, pero con la popularidad de Linux se creo una versión para este sistema llamada Flex.

### Estructura del directorio
```c++
P2
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   ├── ClaseLexica.java // Enum que contendrá todas las clases léxicas
    │   │   └── Token.java // Estructura de datos que representa un componente léxico
    │   └── jflex
    │       └── Lexer.flex // Definición de nuestro analizador léxico
    └── tst
        └── prueba.txt // Archivo de entrada de prueba
```

### Uso

#### Compilación

```bash
$ jflex src/main/jflex/Lexer.flex
$ javac --source-path src -d build src/main/jflex/Lexer.java
```

#### Ejecución

```bash
$ java -cp build main.jflex.Lexer input.txt  
```


#### Salida esperada
```
<INT, int>
<FLOAT, float>
<IF, if>
<ELSE, else>
<WHILE, while>
<INT, int>
<NUMERO, 12345>
<NUMERO, 1.2e6>
<ID, a1>
<ID, a_23>
<ID, ___>
<ID, id2>
<ID, if3>
<ID, while4>
<ID, _b>
<PYC, ;>
<COMA, ,>
<LPAR, (>
<RPAR, )>
<INT, int>
<RPAR, )>
<ID, a>
<ID, _qbcaaa>
```


#### Ejercicios

1. Describir el conjunto de terminales en y la expresión regular que reconoce a cada uno  en _lexer.flex_. (4 pts)
2. Generar acciones léxicas para cada terminal de nuestro lenguaje en _Lexer.cpp_, de modo que se muestre en pantalla la salida esperada con el archivo _prueba.txt_. (4 pts)
3. Crear un _build.xml_ para Java Ant que suplemente los pasos de compilación y ejecución descritos. (2 pt)

---
#### Extras

4. Modificar lo necesario para producir una salida que considere no guardar lexemas que son los únicos miembros de su clase léxica. (0.5 pts.)
5. Documentar el código. (0.25 pts.)
6. Proponer 4 archivos de prueba nuevos, 2 válidos y 2 inválidos. (0.25 pts.)



