package esjson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Employee {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;
    private List<String> cities;
    private Map<String, String> properties;

    static String[] getFieldsName() {
        return new String[]{"id", "name"};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(long[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Object[] getAsArray() {
        return new Object[]{id, name};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id=").append(id).append(" - ");
        sb.append("name=").append(name).append(" - ");
        sb.append("permanent=").append(permanent).append(" - ");
        sb.append("address=").append(address).append(" - ");
        sb.append("phoneNumbers=").append(Arrays.toString(phoneNumbers)).append(" - ");
        sb.append("role=").append(role).append(" - ");
        sb.append("cities=").append(cities).append(" - ");
        sb.append("properties=").append(properties);

        return sb.toString();
    }
}
