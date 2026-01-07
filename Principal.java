import java.util.Scanner;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== ANALIZADOR LINGÜÍSTICO v1.0 ===");
        System.out.println("Escriba el código a analizar (Finalice con una línea vacía o CTRL+D/Z):");
        
        StringBuilder codigo = new StringBuilder();
        String linea;
        while (sc.hasNextLine()) {
            linea = sc.nextLine();
            if (linea.isEmpty()) break; 
            codigo.append(linea).append(" ");
        }

        // 1. Análisis Léxico
        List<ComponenteLexico> listaTokens = AnalizadorLexico.analizar(codigo.toString());
        
        System.out.println("\n[1] RESULTADO DEL ANÁLISIS LÉXICO:");
        System.out.println("----------------------------------------------");
        for (ComponenteLexico t : listaTokens) {
            System.out.println(t);
        }

        // 2. Análisis Sintáctico
        System.out.println("\n[2] RESULTADO DEL ANÁLISIS SINTÁCTICO:");
        System.out.println("----------------------------------------------");
        try {
            AnalizadorSintactico parser = new AnalizadorSintactico(listaTokens);
            parser.analizar();
            System.out.println("✅ ÉXITO: El código es sintácticamente correcto.");
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
        
        sc.close();
    }
}