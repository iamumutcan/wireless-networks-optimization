import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Class<?> clazz = Class.forName("CoordinateGrouping");
                Method method = clazz.getMethod("main", String[].class);

                String[] arguments = {};

                method.invoke(null, (Object) arguments);

                Thread.sleep(10000); // 10 saniye uyku
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
