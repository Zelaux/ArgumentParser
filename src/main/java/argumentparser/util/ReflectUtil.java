package argumentparser.util;

import lombok.SneakyThrows;

import java.awt.*;
import java.lang.reflect.Field;

public class ReflectUtil {
    @SneakyThrows
    public static <T> T get(Field field) {
        return (T) field.get(null);
    }
}
