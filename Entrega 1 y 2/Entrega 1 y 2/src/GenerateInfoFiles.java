import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    public static void main(String[] args) {
        int randomSalesCount = 10; // Número de ventas pseudoaleatorias por vendedor
        int productsCount = 5; // Número de productos pseudoaleatorios
        int salesmanCount = 3; // Número de vendedores pseudoaleatorios

        // Generar archivos pseudoaleatorios
        boolean success = createSalesMenFiles(randomSalesCount, salesmanCount);
        if (success) {
            success = createProductsFile(productsCount);
            if (success) {
                createSalesManInfoFile(salesmanCount);
            } else {
                System.err.println("Error al generar el archivo de productos.");
            }
        } else {
            System.err.println("Error al generar los archivos de ventas de vendedores.");
        }

        if (success) {
            System.out.println("Archivos generados exitosamente.");
        }
    }

    // Método para generar archivos de ventas de vendedores pseudoaleatorios
    private static boolean createSalesMenFiles(int randomSalesCount, int salesmanCount) {
        try {
            Random random = new Random();
            for (int i = 1; i <= salesmanCount; i++) {
                String fileName = "Vendedor_" + i + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (int j = 1; j <= randomSalesCount; j++) {
                    // Generar datos pseudoaleatorios
                    String saleData = generateRandomSaleData();
                    writer.write(saleData);
                    writer.newLine();
                }
                writer.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para generar datos de venta pseudoaleatorios
    private static String generateRandomSaleData() {
        Random random = new Random();
        String[] documentTypes = {"DNI", "CI", "PASSPORT"};
        String documentType = documentTypes[random.nextInt(documentTypes.length)];
        int documentNumber = 10000000 + random.nextInt(90000000);
        int productCount = 1 + random.nextInt(5);
        StringBuilder saleData = new StringBuilder(documentType + ";" + documentNumber);
        for (int i = 1; i <= productCount; i++) {
            int productId = 1 + random.nextInt(10); // Considerando 10 productos en total
            int quantity = 1 + random.nextInt(10); // Cantidad de productos vendidos
            saleData.append("\n").append("IDProducto").append(productId).append(";").append(quantity);
        }
        return saleData.toString();
    }

    // Método para generar archivo de información de productos pseudoaleatorios
    private static boolean createProductsFile(int productsCount) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Productos.txt"));
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Producto_" + i;
                double pricePerUnit = 10 + (Math.random() * (100 - 10)); // Precio entre 10 y 100
                writer.write("IDProducto" + i + ";" + productName + ";" + pricePerUnit);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para generar archivo de información de vendedores pseudoaleatorios
    private static boolean createSalesManInfoFile(int salesmanCount) {
        try {
            Random random = new Random();
            BufferedWriter writer = new BufferedWriter(new FileWriter("Vendedores.txt"));
            for (int i = 1; i <= salesmanCount; i++) {
                String documentType = "DNI"; // Supongamos que todos los vendedores tienen DNI
                int documentNumber = 10000000 + random.nextInt(90000000);
                String firstName = "Vendedor_" + i;
                String lastName = "Apellido_" + i;
                writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
