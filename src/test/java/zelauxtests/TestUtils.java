package zelauxtests;

import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class TestUtils {
    private static Class<?> realClass(Object e) {
        Class<?> eClass = e.getClass();
        if (eClass.isAnonymousClass()) {
            eClass = eClass.getSuperclass();
        }
        return eClass;
    }

    public static void checkError(String message, Class<? extends Throwable>[] exceptionTypes, Runnable runnable) {
        try {
            runnable.run();
            if (message != null) Assert.fail("Expected error with message: '" + message + "'");
        } catch (Exception e) {
            Class<?> exceptionClass = realClass(e);
            boolean contains = false;
            for (Class<? extends Throwable> exceptionType : exceptionTypes) {
                if (exceptionType == exceptionClass) {
                    contains = true;
                    break;
                }
            }
            Assert.assertTrue("Unexpected exception type " + getStackTrace(e), contains);
            Assert.assertEquals(message, e.getMessage());
        }
    }

    public static String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

//    public static void checkError(Cons<String> messageChecker, Class<? extends Throwable>[] exceptionTypes, Runnable runnable) {
//        try {
//            runnable.run();
//            if (messageChecker != null) Assert.fail("Expected error : '" + Arrays.toString(exceptionTypes) + "'");
//        } catch (Exception e) {
//            Class<?> exceptionClass = realClass(e);
//            Assert.assertTrue("Unexpected exception type " + Strings.getStackTrace(e), Structs.contains(exceptionTypes, exceptionClass));
//            messageChecker.get(e.getMessage());
//        }
//    }
