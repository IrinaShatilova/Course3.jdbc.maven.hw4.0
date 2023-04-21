import config.ConnectionConfig;
import model.City;
import model.Employee;
import service.EmployeeDAO;
import service.EmployeeDAOImpl;
import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        taskOne();
        System.out.println("------ Задание 2 ------");
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        System.out.println("2.3 Получить список всех объектов Employee:");
        List<Employee> employees = employeeDAO.getAllEmployee();
        System.out.println(employees);
        System.out.println("2.1 Создание (добавление) сущности Employee в базу данных:");
        Employee employee = new Employee("Violeta", "Nasheva", "female",
                30, new City(2));
        employeeDAO.add(employee);
        employees = employeeDAO.getAllEmployee();
        employees.forEach(System.out::println);

        System.out.println("2.2 Получение конкретного объекта Employee по id:");
        employee = employeeDAO.getById(10);
        System.out.println(employee);

        System.out.println("2.4 Изменение конкретного объекта Employee в базе по id:");
        employeeDAO.updateEmployee(13, new Employee("Vanya", "Vladov", "male", 30, new City(4)));
        System.out.println(employeeDAO.getById(13));

        System.out.println("2.5 Удаление конкретного объекта Employee из базы по id:");
        employeeDAO.deleteById(13);
        employees = employeeDAO.getAllEmployee();
        employees.forEach(System.out::println);
    }
        public static void taskOne () {
            System.out.println("------ Задание 1 ------");
            try (PreparedStatement statement = ConnectionConfig.getConnection().prepareStatement(
                    "SELECT * FROM employee WHERE id = ?")) {
                System.out.println("Соединение с базой данных установлено");
                statement.setInt(1, 6); // Указать ID
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String gender = resultSet.getString("gender");
                    String age = resultSet.getString(5);
                    String city = resultSet.getString(6);

                    System.out.println("Name: " + firstName + " " + lastName);
                    System.out.println("Gender: " + gender);
                    System.out.println("Age: " + age);
                    System.out.println("City: " + city);
                } else {
                    System.out.println("Заданный ID отсутсвует в базе данных ");
                }
            } catch (SQLException e) {
                System.out.println("Ошибка подключения к базе данных " + e.getMessage());
            }
        }
    }
