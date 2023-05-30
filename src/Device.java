public class Device {
    public int id;
    public double x;
    public double y;
    public boolean isConnected;
    public Device(int id,double x, double y) {
        this.id=id;
        this.x = x;
        this.y = y;
        this.isConnected = false;
    }
    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", isConnected=" + isConnected +
                '}';
    }
}
