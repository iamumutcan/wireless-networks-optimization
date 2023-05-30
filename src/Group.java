import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Group {
    public int id;
    public double x;
    public double y;
    public  int maxCapacity;
    public double coverageRadius;
    List<Device> connectedDevices;

    public Group(int id, double x, double y, int maxCapacity, double coverageRadius, List<Device> connectedDevices) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.maxCapacity = maxCapacity;
        this.coverageRadius = coverageRadius;
        this.connectedDevices = connectedDevices;
    }

}
