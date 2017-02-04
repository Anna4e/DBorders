import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by Anna on 02.02.2017.
 */
public class NewOrder {

    private static int orderId;
    private static String customer;
    private static int customerId;
    private static String product_name = "";
    private static int productId = 0;
    private static double price;
    static UUID Id = UUID.randomUUID();

    public static void main(String[] args) throws SQLException {

        orderId = Integer.getInteger(String.valueOf(Id));
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product ID or product name:");
        String string = sc.nextLine();
        productId = Integer.parseInt(string);
        product_name = string;
        String [] product = (String[]) Products.getProduct(productId, product_name).toArray();
        int amount = Integer.parseInt(product[2]);
        if (amount > 0) {
            amount--;
            productId = Integer.parseInt(product[0]);
            product_name = product[1];
            price = Double.parseDouble(product[3]);
            Products.addProduct(productId, product_name, amount, price);
            System.out.println("Enter customer name:");
            string = sc.nextLine();
            customer = string;
            System.out.println("Enter customer fone_number:");
            string = sc.nextLine();
            customerId = Integer.parseInt(string);
            System.out.println("Enter customer home_adress:");
            string = sc.nextLine();
            String home_adress = string;
            Customers.addCustomer(orderId, customer, customerId, home_adress);
            Orders.addOrder(orderId, customer, customerId, product_name,
                    productId, price);

        }else{
            System.out.println("Product is not available in stock");
                    }


    }

    public NewOrder() {

    }




}
