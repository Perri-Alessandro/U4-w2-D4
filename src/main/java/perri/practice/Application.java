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

        System.out.println("---------PRODOTTI IN MAGAZZINO----------");
        System.out.println(magazzino);

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
                        cliente -> generaOrdiniCasualiPerCliente(cliente)
                ));

        System.out.println("-----------ORDINI DI VARI CLIENTI-------------");
        ordiniClienti.forEach((cliente, ordini) -> {
            System.out.println("CLIENTE: " + cliente);
            if (ordini.isEmpty()) {
                System.out.println(" Nessun ordine per questo cliente.");
            } else {
                ordini.forEach(ordine -> log.info("Generato ordine: {}", ordine));

            }
        });


        Map<Customer, Double> totOgniCliente = new HashMap<>();
        ordiniClienti.forEach((cliente, ordini) -> {
            double totale = ordini.stream()
                    .mapToDouble(ordine -> ordine.getProduct().price).sum();
            totOgniCliente.put(cliente, totale);
        });

        System.out.println("---------TOT PER OGNI CLIENTE ----------");
        totOgniCliente.forEach((cliente, totale) -> {
            System.out.println("CLIENTE: " + cliente + ", TOTALE SPESO: " + totale);
        });

        System.out.println("---------PRODOTTI PIù COSTOSI IN MAGAZZINO----------");
        List<Product> piùCostosi = magazzino.stream().filter(product -> product.getPrice()
                        > magazzino.stream().collect(Collectors.averagingDouble(Product::getPrice)))
                .toList();

        piùCostosi.forEach(product -> log.info(String.valueOf(product)));


        sc.close();

    }

    private static List<Order> generaOrdiniCasualiPerCliente(Customer cliente) {
        if (magazzino.isEmpty()) {
            return Collections.emptyList();
        }
        int numOrdini = random.nextInt(3) + 1;
        List<Order> ordiniPerCliente = new ArrayList<>();
        for (int i = 0; i < numOrdini; i++) {
            Product prodottoCasuale = magazzino.get(random.nextInt(magazzino.size()));
            Order ordine = new Order(
                    "StatoCasuale",
                    cliente,
                    prodottoCasuale,
                    LocalDate.now().minusDays(random.nextInt(30) + 1),
                    LocalDate.now().plusDays(random.nextInt(30) + 1)
            );
            ordiniPerCliente.add(ordine);
        }
        return ordiniPerCliente;
    }


}
