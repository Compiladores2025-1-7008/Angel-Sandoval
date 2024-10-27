import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import main.jflex.Lexer;  
import main.byacc.Parser;

public class Main {
    public static void main(String[] args) {
        try {
            Reader reader;

            // Selecciona el lector: archivo si se proporciona, o entrada estándar
            if (args.length > 0) {
                reader = new FileReader(args[0]);
                System.out.println("Leyendo desde el archivo: " + args[0]);
            } else {
                reader = new InputStreamReader(System.in);
                System.out.println("Leyendo desde la entrada estándar. Introduzca la expresión: ");
            }
            
            // Crear y ejecutar el parser
            Parser parser = new Parser(reader);
            parser.parse();
            
        } catch (Exception e) {
            System.err.println("Error al ejecutar la calculadora: " + e.getMessage());
        }
    }
}
