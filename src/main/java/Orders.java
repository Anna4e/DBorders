import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Anna on 02.02.2017.
 */
public class Orders {
        private int orderId;
        private String customer;
        private int customerId;
        private String product_name;
        private int productId;
        private double price;

    public Orders(int orderId, String customer, int customerId, String product_name, int productId, double price) {
        this.orderId = orderId;
        this.customer = customer;
        this.customerId = customerId;
        this.product_name = product_name;
        this.productId = productId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

     public int getCustomerId() {
        return customerId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }


    //полный список заказов
        public static void wholeRangeOfOrders() throws SQLException {
            PreparedStatement ps = null;
            try {
                ps = DbProperties.getConn().prepareStatement("SELECT * FROM orders");
                setResult(ps);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ps.close();
            }
        }



        // добавить заказ
        public static String addOrder(int orderId, String customer, int customerId, String product_name,
                                      int productId, double price) throws SQLException{
            String result = "";
            PreparedStatement ps = null;
            //insert
                try {
                    ps = DbProperties.getConn().prepareStatement("INSERT INTO Orders(orderId, customer, customerId, product_name, productId, price) VALUES (?,?,?,?,?,?)");
                    ps.setInt(1, orderId);
                    ps.setString(2, customer);
                    ps.setInt(3, customerId);
                    ps.setString(4, product_name);
                    ps.setInt(5, productId);
                    ps.setString(6, Double.toString(price));

                }
                catch (SQLException e){
                    System.out.println("The order was not added");
                }finally {
                    ps.close();
                }
              return "The order was successfully added! Yor order number is " + orderId;
        }


        private static void setResult(PreparedStatement ps) throws SQLException {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(md.getColumnName(i) + "\t\t");
                    System.out.println();
                    while (rs.next()) {
                        for (i = 1; i <= md.getColumnCount(); i++) {
                            System.out.print(md.getColumnName(i) + "\t\t");
                        }
                        System.out.println();
                    }

                }
            } finally {
                rs.close();
            }
        }


}
