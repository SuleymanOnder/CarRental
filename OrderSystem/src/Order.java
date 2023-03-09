import java.sql.*;

/**
 * Created by Suleyman on 2020-10-17.
 */
public class Order extends BaseFunctions{

    public Order(Connection con) {
        super(con);
    }

    public void displayOrders() {
        System.out.println("\nOrder:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM public.Order ORDER BY ReturnDate");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnCar(String carId) {
        try {
            String sql = "UPDATE public.Order SET \"ReturnDate\" = CURRENT_DATE WHERE \"car\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setString(1, carId);
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR: " + e.getMessage());
        }
    }

    public void createOrder(String carId, long id) {
        //INSERT INTO Car VALUES(default, '37508097779', 'HUB007', '2020-10-19');
        String sql = "INSERT INTO public.Order VALUES (default , ?, ?, CURRENT_DATE )";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, carId);
            //statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.execute();
            commit();
            System.out.println("Success");
        } catch (SQLException e) {
            if (connection != null) {
                rollback();
            }
            System.out.println("INSTALL ERROR: " + e.getMessage());
        } finally {
            setAutoCommit(true);
        }
    }
}
