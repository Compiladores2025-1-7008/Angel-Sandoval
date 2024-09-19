import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AnalizadorLexico {
    // Definición de patrones con expresiones regulares
    private static final Pattern ID = Pattern.compile("[a-zA-Z]+");
    private static final Pattern ENTERO = Pattern.compile("[1-9][0-9]*|0");
    private static final Pattern REAL = Pattern.compile("[1-9][0-9]*\\.[0-9]+|0\\.[0-9]+|\\.[0-9]+");
    private static final Pattern SUMA = Pattern.compile("\\+");
    private static final Pattern ASIGNACION = Pattern.compile("=");

    // Variables del analizador léxico
    private int estado = 0;
    private int posicion = 0;
    private String fuente = "";
    private char caracter;
    private String lexema = "";

    // Lista de tokens
    private ArrayList<Token> listaTokens = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Formato de Entrada: java AnalizadorLexico <prueba.txt>");
            return;
        }

        AnalizadorLexico analizador = new AnalizadorLexico();
        analizador.iniciarConArchivo(args[0]);
    }

    // Método para manejar la lectura del archivo de entrada
    public void iniciarConArchivo(String archivo) {
        try {
            File file = new File(archivo);
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();

            // Leer todo el archivo
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            fuente = sb.toString().trim();

            // Verificar si el texto fuente no está vacío
            if (fuente.length() == 0) {
                System.out.println("El archivo está vacío.");
            } else {
                iniciarProceso();
                imprimirTokens();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + archivo);
        }
    }

    private void iniciarProceso() {
        while (posicion < fuente.length()) {
            caracter = fuente.charAt(posicion);

            switch (estado) {
                case 0:
                    if (Character.isWhitespace(caracter)) {
                        // Ignorar espacios en blanco
                        posicion++;
                    } else if (Character.isLetter(caracter)) {
                        estado = 1;
                        lexema += caracter;
                        posicion++;
                    } else if (Character.isDigit(caracter)) {
                        estado = 2;
                        lexema += caracter;
                        posicion++;
                    } else if (caracter == '.') {
                        lexema += caracter;
                        estado = 3;  // Estado para manejar reales que comienzan con '.'
                        posicion++;
                    } else if (caracter == '+') {
                        addToken("SUMA", "+");
                        posicion++;
                    } else if (caracter == '=') {
                        addToken("ASIG", "=");
                        posicion++;
                    } else {
                        error();
                        posicion++;
                    }
                    break;

                case 1:  // Identificadores
                    if (Character.isLetter(caracter)) {
                        lexema += caracter;
                        posicion++;
                    } else {
                        if (ID.matcher(lexema).matches()) {
                            addToken("ID", lexema);
                        } else {
                            error();
                        }
                        lexema = "";
                        estado = 0;
                    }
                    break;

                case 2:  // Enteros o números reales con parte entera
                    if (Character.isDigit(caracter)) {
                        lexema += caracter;
                        posicion++;
                    } else if (caracter == '.') {
                        lexema += caracter;
                        estado = 3;  // Cambiar a estado para manejar la parte fraccionaria
                        posicion++;
                    } else {
                        if (ENTERO.matcher(lexema).matches()) {
                            addToken("ENT", lexema);
                        } else {
                            error();
                        }
                        lexema = "";
                        estado = 0;
                    }
                    break;

                case 3:  // Números reales (parte fraccionaria)
                    if (Character.isDigit(caracter)) {
                        lexema += caracter;
                        posicion++;
                    } else {
                        if (REAL.matcher(lexema).matches()) {
                            addToken("REAL", lexema);
                        } else {
                            error();
                        }
                        lexema = "";
                        estado = 0;
                    }
                    break;

                default:
                    error();
                    estado = 0;
                    break;
            }
        }

        // Verificar si queda algún lexema por procesar
        if (estado == 1 && ID.matcher(lexema).matches()) {
            addToken("ID", lexema);
        } else if (estado == 2 && ENTERO.matcher(lexema).matches()) {
            addToken("ENT", lexema);
        } else if (estado == 3 && REAL.matcher(lexema).matches()) {
            addToken("REAL", lexema);
        }
    }

    // Método para agregar un token a la lista
    private void addToken(String tipo, String lexema) {
        listaTokens.add(new Token(tipo, lexema));
    }

    // Método para imprimir la lista de tokens
    private void imprimirTokens() {
        System.out.println("Token , Lexema");
        for (Token token : listaTokens) {
            System.out.println(token.getTipo() + ", " + token.getLexema());
        }
    }

    // Método para manejar errores léxicos
    private void error() {
        System.out.println("Error léxico en: " + lexema);
    }
}
