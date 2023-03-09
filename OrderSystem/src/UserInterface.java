import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by Suleyman on 2020-10-17.
 */
public class UserInterface {
    private Connection connection;

    private Car car;
    private Client client;
    private Model model;
    private Order order;

    BufferedReader input =  new BufferedReader(new InputStreamReader(System.in));

    public UserInterface(Car car, Client client, Model model, Order order, Connection con){
        this.car = car;
        this.model = model;
        this.client = client;
        this.order = order;
        this.connection = con;
    }

    public void start(){
        try {
            showMenu();
            System.out.print("\nSelect: ");
            String choice = input.readLine();

            while(true){
                switch(choice){
                    case "0":
                        exit(connection);
                        break;
                    case "1":
                        client.displayClients();
                        break;
                    case "2":
                        addClient();
                        break;
                    case "3":
                        updateClientAddress();
                        break;
                    case "4":
                        updateClientPhoneNumber();
                        break;
                    case "5":
                        findClientById();
                        break;
                    case "6":
                        removeClientById();
                        break;
                    case "7":
                        car.displayCars();
                        break;
                    case "8":
                        addModel();
                        break;
                    case "9":
                        addCar();
                        break;
                    case "10":
                        updateCarPrice();
                        break;
                    case "11":
                        removeCarById();
                        break;
                    case "12":
                        order.displayOrders();
                        break;
                    case "13":
                        createOrder();
                        break;
                    case "14":
                        returnCar();
                        break;
                    default: System.out.println("Wrong Choose!");
                        break;
                }
                showMenu();
                System.out.print("\nSet Select: ");
                choice = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createOrder() throws IOException {
        car.showAvailableCars();
        System.out.println("Select one car (enter the licence_plate");
        String carId = input.readLine();
        client.displayClients();
        System.out.println("Client ID no");
        long id = Long.parseLong(input.readLine());
        order.createOrder(carId, id);
    }

    private void returnCar() throws IOException {
        order.displayOrders();
        System.out.println("Licence place of return car");
        String carId = input.readLine();
        order.returnCar(carId);
    }

    private void removeCarById() throws IOException {
        car.displayCars();
        System.out.println("Licence_plate of delete car");
        String carId = input.readLine();
        car.remove(carId);
    }

    private void updateCarPrice() throws IOException {
        car.displayCars();
        System.out.println("Licence_plate of update car's price");
        String carId = input.readLine();
        System.out.println("Yeni Fiyat");
        int price = Integer.parseInt(input.readLine());
        car.updatePrice(carId, price);
    }

    private void addCar() throws IOException {
        model.displayModels();
        System.out.println("Enter the Model Information");
        int id = Integer.parseInt(input.readLine());
        System.out.println("Enter the Licence_plate");
        String carId = input.readLine();
        System.out.println("Enter the Price");
        int price = Integer.parseInt(input.readLine());
        car.addCar(id, carId, price);
    }

    private void removeClientById() throws IOException {
        System.out.println("ID:");
        long id = Long.parseLong(input.readLine());
        client.removeClient(id);
    }

    private void findClientById() throws IOException {
        System.out.println("ID:");
        long id = Long.parseLong(input.readLine());
        client.findClientById(id);
    }

    private void updateClientPhoneNumber() throws IOException {
        client.displayClients();
        System.out.println("Client ID:");
        long id = Long.parseLong(input.readLine());
        System.out.println("Phone:");
        String phone = input.readLine();
        client.updatePhoneNumber(id, phone);
    }

    private void updateClientAddress() throws IOException {
        client.displayClients();
        System.out.println("Client ID:");
        long id = Long.parseLong(input.readLine());
        System.out.println("Address:");
        String address = input.readLine();
        client.updateAddress(id, address);
    }

    private void addModel() throws IOException {
        System.out.println("Manufacturer:");
        String brand = input.readLine();
        System.out.println("Model");
        String models = input.readLine();
        System.out.println("Manufacturing Year");
        int year = Integer.parseInt(input.readLine());
        model.addModel(brand, models, year);
    }

    private void addClient() throws IOException {
        System.out.println("ID:");
        long id = Long.parseLong(input.readLine());

        System.out.println("Name");
        String name = input.readLine();

        System.out.println("Surname");
        String surname = input.readLine();

        System.out.println("Phone");
        String phone = input.readLine();

        System.out.println("Address");
        String address = input.readLine();

        client.addClient(id, name, surname, phone, address);
    }

    private void showMenu(){
        System.out.println();
        System.out.println("+-------------------------------------+");
        System.out.println("|********Car*Rental*System********|");
        System.out.println("+-------------------------------------+");
        System.out.println("|      Client management system       |");
        System.out.println("+-------------------------------------+");
        System.out.println("|  1. Show the Client 				  |");
        System.out.println("|  2. Add a new client		          |");
        System.out.println("|  3. Update client address		      |");
        System.out.println("|  4. Update client phone		      |");
        System.out.println("|  5. Search Client                   |");
        System.out.println("|  6. Delete Client                   |");
        System.out.println("+-------------------------------------+");
        System.out.println("|      Car management system          |");
        System.out.println("+-------------------------------------+");
        System.out.println("|  7. Show the car                    |");
        System.out.println("|  8. Add a new model	              |");
        System.out.println("|  9. Add a new car                   |");
        System.out.println("| 10. Update the car's price          |");
        System.out.println("| 11. Delete the car                  |");
        System.out.println("+-------------------------------------+");
        System.out.println("|       Order management system       |");
        System.out.println("+-------------------------------------+");
        System.out.println("| 12. Show the orders		          |");
        System.out.println("| 13. Order car		                  |");
        System.out.println("| 14. Return car         	          |");
        System.out.println("+-------------------------------------+");
        System.out.println("| 0. Quit the system                  |");
        System.out.println("+-------------------------------------+");
    }

    public void exit(Connection con){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can not close connection!");
                e.printStackTrace();
            }
        }
        System.exit(1);
    }
}

