package esjson;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        EmployeeManager manager = new EmployeeManager(Paths.get("esjson/input.json"));
        new JacksonGui(manager).setVisible(true);
    }
}
