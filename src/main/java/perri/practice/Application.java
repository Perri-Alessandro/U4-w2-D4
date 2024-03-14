package perri.practice;


import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import perri.practice.Entities.Customer;
import perri.practice.Entities.Order;
import perri.practice.Entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final List<Product> magazzino = new ArrayList<>();

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product uno = new Product("LIBRO UNO", "BOOKS", 130);
        Product due = new Product("LIBRO CINQUE", "BOOKS", 98.7);
        Product tre = new Product("LIBRO TRE", "BOOKS", 179.41);
        Product quattro = new Product("LIBRO QUATTRO", "BOOKS", 68.20);
        Product cinque = new Product("LIBRO DUE", "BOOKS", 102.99);
        Product sei = new Product("BIBERON", "BABY", 22.4);
        Product sette = new Product("CULLA", "BABY", 160.88);
        Product otto = new Product("CIBO PER CANI", "ANIMALI", 42.80);
        Product nove = new Product("LEDGER NANO X", "SICUREZZA", 98.70);
        Product dieci = new Product("GIOCO BABY", "BABY", 26.40);
        Product undici = new Product("BOXER", "BOYS", 8.20);
        Product dodici = new Product("XXX", "BOYS", 22.4);

        List<Product> magazzino = new ArrayList<>();
        magazzino.add(uno);
        magazzino.add(due);
        magazzino.add(tre);
        magazzino.add(quattro);
        magazzino.add(cinque);
        magazzino.add(sei);
        magazzino.add(sette);
        magazzino.add(otto);
        magazzino.add(nove);
        magazzino.add(dieci);
        magazzino.add(undici);
        magazzino.add(dodici);

        Supplier<Integer> randomUser = () -> {
            Random rndm = new Random();
            return rndm.nextInt(1, 11);
        };

        Supplier<Customer> userSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            return new Customer(faker.name().fullName());
        };

        int userCount = randomUser.get();

        List<Customer> clientiList = new ArrayList<>();

        for (int i = 0; i < userCount; i++) {
            clientiList.add(userSupplier.get());
        }
        System.out.println("-----------CLIENTI--------------");
        clientiList.forEach(clienti -> System.out.println(clienti));

        Map<Customer, List<Order>> ordiniClienti = clientiList.stream()
                .collect(Collectors.toMap(
                        cliente -> cliente,
                        order -> generaOrdiniCasualiPerCliente(order)
                ));

        ordiniClienti.forEach((cliente, ordini) -> {
            System.out.println("CLIENTE: " + cliente + " ha " + ordini.size() + " ordini.");
            ordini.forEach(ordine -> System.out.println(" ORDINE: " + ordine));
        });


        //////////////
        System.out.println("ordini " + ordiniClienti);

        if (!magazzino.isEmpty()) {
            Product prodottoCasuale = magazzino.get(random.nextInt(magazzino.size()));
            Order ordine = new Order(
                    "StatoCasuale",
                    clientiList.get(0),
                    prodottoCasuale,
                    LocalDate.now().minusDays(5),
                    LocalDate.now().plusDays(5)
            );
            System.out.println(ordine);
        }

        //////////////

        sc.close();

    }

    private static List<Order> generaOrdiniCasualiPerCliente(Customer cliente) {
        if (magazzino.isEmpty() || cliente == null) {
            return Collections.emptyList();
        }

        Product prodottoCasuale = magazzino.get(random.nextInt(magazzino.size()));
        Order ordine = new Order(
                "StatoCasuale",
                cliente,
                prodottoCasuale,
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(5)
        );
        return Collections.singletonList(ordine);
    }


}
