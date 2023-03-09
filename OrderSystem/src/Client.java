import java.sql.*;

/**
 * Created by Suleyman on 2020-10-17.
 */
public class Client extends BaseFunctions{

    public Client(Connection con) {
        super(con);
    }

    protected void displayClients() {
        System.out.println("\nClients:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM public.Clients");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addClient(long id, String name, String surname, String phone, String address) {
        String sql = "INSERT INTO public.Clients VALUES (?, ?, ?, ?, ?)";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, phone);
            statement.setString(5, address);
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

    public void updateAddress(long id, String address) {
        try {
            String sql = "UPDATE public.Clients SET \"address\" = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address);
            statement.setString(2, String.valueOf(id));
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR: " + e);

        }
    }

    public void updatePhoneNumber(long id, String phone) {
        try {
            String sql = "UPDATE public.Clients SET \"Phone\" = ? WHERE \"ID\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setString(2, String.valueOf(id));
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR: " + e.getMessage());
        }
    }

    public void findClientById(long id) {
        try {
            String sql = "SELECT * FROM public.Clients WHERE \"ID\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(long id) {
        setAutoCommit(false);
        try {
            String sql = "DELETE FROM public.Clients WHERE \"ID\" = ?";
            String sql2 = "DELETE FROM public.Order WHERE \"Client\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement.setString(1, String.valueOf(id));
            statement2.setString(1, String.valueOf(id));
            statement2.execute();
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            if (connection != null) {
                rollback();
            }
            System.out.println("REMOVE ERROR: " + e.getMessage());
        } finally {
            setAutoCommit(true);
        }
    }
}
