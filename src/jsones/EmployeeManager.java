package jsones;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employees;
    private final Path path;

    public EmployeeManager(Path path) throws IOException {
        this.path = path;

        this.employees = new ArrayList<>();

        loadFromFile();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void loadFromFile() throws IOException {
        byte[] jsonData = Files.readAllBytes(path);

        ObjectMapper objectMapper = new ObjectMapper();

        Employee[] loaded = objectMapper.readValue(jsonData, Employee[].class);

        employees.addAll(Arrays.asList(loaded));
    }

    public Employee getEmployee(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removeEmployee(int id) {
        employees.removeIf(e -> e.getId() == id);
    }
}
