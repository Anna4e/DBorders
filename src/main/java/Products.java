import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 02.02.2017.
 */
public class Products {
private int id;
private String product_name;
private int amount;
private double price;


      public Products(int id, String product_name, int amount, double price) {
        this.id = id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
    }



    public int getId() {
        return id;
    }

       public String getProduct_name() {
        return product_name;
    }

      public int getAmount() {
        return amount;
    }

      public double getPrice() {
        return price;
    }


    //полный список услуг
    public static void wholeRangeOfProducts() throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = DbProperties.getConn().prepareStatement("SELECT * FROM products");
            setResult(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }


    //получить инфо по товару
    public static List<String> getProduct(int id, String product_name) throws SQLException{
        List<String> result = new ArrayList<String>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DbProperties.getConn().prepareStatement("SELECT * FROM Products WHERE id = ? or product_name = ?");
            ps.setInt(1, id);
            ps.setString(2, product_name);
            try {
                rs = ps.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    result.add(rs.toString());
                    while (rs.next()) {
                        for (i = 1; i <= md.getColumnCount(); i++) {
                            result.add(rs.toString());
                        }

                    }

                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
            rs.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
                }
        return result;
    }


// добавить товар
    public static String addProduct(int id, String product_name, int amount, double price) throws SQLException{
          String result = "";
        PreparedStatement ps = null;
        List<String> resultGet = getProduct(id, product_name);
          if (!resultGet.isEmpty()) {
              //update
              ps = DbProperties.conn.prepareStatement("UPDATE Product SET amount = ? WHERE id = ?");
              try {
                  ps.setInt(1, amount);
                  ps.setInt(2, id);
                  ps.executeUpdate(); // for INSERT, UPDATE & DELETE
              } finally {
                  ps.close();
              }
          }else{
              //insert
                try {
                  ps = DbProperties.getConn().prepareStatement("INSERT INTO Products (id, product_name, amount, price) VALUES (?,?,?,?)");
                  ps.setInt(1, id);
                  ps.setString(2, product_name);
                  ps.setInt(3, amount);
                  ps.setString(4, Double.toString(price));
              }
                  catch (SQLException e){
                  System.out.println("The product was not added");
                  }finally {
                      ps.close();
                }
          }
          return "The product was successfully added!";
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
