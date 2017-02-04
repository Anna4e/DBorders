import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Anna on 02.02.2017.
 */
public class Customers {
    private int orderId;
    private String name;
    private int fone_number;
    private String home_adress;

    public Customers(int orderId, String name, int fone_number, String home_adress) {
        this.orderId = orderId;
        this.name = name;
        this.fone_number = fone_number;
        this.home_adress = home_adress;
    }

    public int getOrderId() {
        return orderId;
    }



    public String getName() {
        return name;
    }



    public int getFone_number() {
        return fone_number;
    }



    public String getHome_adress() {
        return home_adress;
    }




    public static void wholeCustomers() throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = DbProperties.getConn().prepareStatement("SELECT * FROM clients");
            setResult(ps);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }



    public static String addCustomer(int orderId, String name, int fone_number, String home_adress) throws SQLException{
        String result = "";
        PreparedStatement ps = null;
               try {
                ps = DbProperties.getConn().prepareStatement("INSERT INTO clients (orderId, name, fone_number, home_adress) VALUES (?,?,?,?)");
                ps.setInt(1, orderId);
                ps.setString(2, name);
                ps.setInt(3, fone_number);
                ps.setString(4, home_adress);
            }
            catch (SQLException e){
                System.out.println("The customer was not added");
            }finally {
                ps.close();
            }
            return "The customer was successfully added!";
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
