package ch.idsia;

import org.apache.commons.lang3.math.Fraction;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class util {
    public static double parseDouble(String s){
        if (s.contains("/")){
            String[] frac = s.split("/");
            return Double.valueOf(frac[0]) / Double.valueOf(frac[1]);
            //  return Fraction.getFraction(s).doubleValue();
        }
        return Double.valueOf(s);
    }

    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}
