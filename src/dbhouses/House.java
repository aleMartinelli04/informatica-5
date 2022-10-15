package dbhouses;

import java.sql.ResultSet;
import java.sql.SQLException;

public record House(int id, String street, String city, int cityCAP, boolean rentable, double price, boolean available,
                    String renterName) {

    public static House fromResultsSet(ResultSet set) throws SQLException {
        return new House(
                set.getInt("id"),
                set.getString("street"),
                set.getString("city"),
                set.getInt("cityCAP"),
                set.getBoolean("rentable"),
                set.getDouble("price"),
                set.getBoolean("available"),
                set.getString("renterName")
        );
    }

    public static String[] getColumnIdentifiers() {
        return new String[]{"id", "location"};
    }

    public String getFullLocation() {
        return street + ", " + city + " (CAP " + cityCAP + ")";
    }

    public Object[] getAsArray() {
        return new Object[]{id, getFullLocation()};
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id + ", " +
                "street=" + street + ", " +
                "city=" + city + ", " +
                "cityCAP=" + cityCAP + ", " +
                "rentable=" + rentable + ", " +
                "price=" + price + ", " +
                "available=" + available + ", " +
                "renterName=" + renterName + '}';
    }
}
