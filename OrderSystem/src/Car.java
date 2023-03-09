import java.sql.*;

/**
 * Created by Suleyman on 2020-10-17.
 */

public class Car  extends BaseFunctions{

    public Car(Connection con) {
        super(con);
    }

    public void displayCars() {
        System.out.println("\nCar:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM public.CarInformation ORDER  BY Licence_plate");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(int id, String carId, int price) {
        //INSERT INTO Car VALUES('ABC123', 1, 140);
        String sql = "INSERT INTO public.Car VALUES (?, ?, ?)";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carId);
            statement.setInt(2, id);
            statement.setInt(3, price);
            statement.execute();
            commit();
            System.out.println("Success");
        } catch (SQLException e) {
            if (connection != null) {
                rollback();
            }
            System.out.println("INSTALLATION ERROR: " + e.getMessage());
        } finally {
            setAutoCommit(true);
        }
    }

    public void updatePrice(String carId, int price) {
        try {
            String sql = "UPDATE public.Car SET \"ucret\" = ? WHERE \"Licence_plate\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, carId);
            statement.setInt(1, price);
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR: " + e.getMessage());
        }
    }

    public void remove(String carId) {
        try {
            String sql = "DELETE FROM public.Car WHERE \"Licence_plate\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("REMOVE ERROR: " + e);
        }
    }

    public void showAvailableCars() {
        System.out.println("\nCar:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM public.CarInformation WHERE Licence_plate NOT IN ( SELECT car from public.Order where ReturnDate is NULL);");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
