package jsones;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        EmployeeManager manager = new EmployeeManager(Paths.get("jsones/input.json"));
        new JacksonGui(manager).setVisible(true);
    }
}
