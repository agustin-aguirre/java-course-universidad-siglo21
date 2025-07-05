import Factories.FixedDataSizeBookFactory;
import Repositories.ArrayBookRepository;
import Repositories.ArrayListBookRepository;
import Services.SimpleLoanManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("==========ARRAY DEMO=============");
        runCRUDDemoWithArray();
        System.out.println("===============================");

        System.out.println("==========ARRAYLIST DEMO=============");
        runCRUDDemoWithArrayList();
        System.out.println("===============================");
    }

    private static void runCRUDDemoWithArray() {
        var factory = new FixedDataSizeBookFactory();
        var repo = new ArrayBookRepository(factory);
        var loanManager = new SimpleLoanManager(repo);
    }


    private static void runCRUDDemoWithArrayList() {
        var factory = new FixedDataSizeBookFactory();
        var repo = new ArrayListBookRepository(factory);
        var loanManager = new SimpleLoanManager(repo);

    }
}


// Subir a youtube ?
// Entrega: Armar un video en Youtube explicando el código fuente y ejecutando
// el mismo. En la explicación es necesario que expliquen los conceptos teóricos
// empleados. También deben enviar el código.
// Los que manejan el concepto GIT lo pueden hacer por ahí, sino en archivos está bien.
// Plazo de entrega máximo  lunes 7/07, 9.30 hs