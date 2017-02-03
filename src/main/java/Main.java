import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Anna on 01.02.2017.
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            try {
                // connection
                dbProperties.getConn();
                     while (true) {
                    System.out.println("1: new order");
                    System.out.println("2: show all products");
                    System.out.println("3: show all customers");
                    System.out.println("4: show all orders");
                     System.out.println("5: add new product or change info of product");
                    String param = sc.nextLine();


                    if (param.equals("1")) {
                        new newOrder();
                    }
                    else if (param.equals("2")) {
                        products.wholeRangeOfProducts();

                    } else if (param.equals("3")) {
                       customers.wholeCustomers();

                    } else if (param.equals("4")) {
                        orders.wholeRangeOfOrders();

                    }  else if (param.equals("5")) {
                        System.out.println("Enter product ID");
                        String string = sc.nextLine();
                        int id = Integer.parseInt(string);
                        System.out.println("Enter product name");
                         string = sc.nextLine();
                        String product_name = string;
                        System.out.println("Enter amount");
                         string = sc.nextLine();
                        int amount = Integer.parseInt(string);
                        System.out.println("Enter the price");
                        string = sc.nextLine();
                        double price = Double.parseDouble(string);
                        products.addProduct(id, product_name, amount, price);

                    } else {
                        return;
                    }
                }
            } finally {
                sc.close();
                if (dbProperties.conn != null){

                    dbProperties.conn.close();
                };
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }


}
