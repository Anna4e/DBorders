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
                DbProperties.getConn();
                     while (true) {
                    System.out.println("1: new order");
                    System.out.println("2: show all Products");
                    System.out.println("3: show all customers");
                    System.out.println("4: show all Orders");
                     System.out.println("5: add new product or change info of product");
                    String param = sc.nextLine();


                    if (param.equals("1")) {
                        new NewOrder();
                    }
                    else if (param.equals("2")) {
                        Products.wholeRangeOfProducts();

                    } else if (param.equals("3")) {
                       Customers.wholeCustomers();

                    } else if (param.equals("4")) {
                        Orders.wholeRangeOfOrders();

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
                        Products.addProduct(id, product_name, amount, price);

                    } else {
                        return;
                    }
                }
            } finally {
                sc.close();
                if (DbProperties.conn != null){

                    DbProperties.conn.close();
                };
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }


}
