public class BaseStation {
    public int id;
    public int  maxCapacity;
    public double coverageRadius;
    public boolean isGrouped;
    public BaseStation(int id, int  Capacity, double Radius) {
        this.id=id;
        this.maxCapacity = Capacity;
        this.coverageRadius = Radius;
        this.isGrouped = false;
    }
}
