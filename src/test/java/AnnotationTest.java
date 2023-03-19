import com.an.annotation.MyAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {


    public static void main(String[] args) {
        try {
            Class<?> loadClass = AnnotationTest.class
                    .getClassLoader()
                    .loadClass("com.an.annotation.UseMyAnnotation");
            if (loadClass.isAnnotationPresent(MyAnnotation.class)) {
                Annotation[] declaredAnnotations = loadClass.getDeclaredAnnotations();
                for (Annotation annotation : declaredAnnotations) {
                    System.out.println("Annotation in class '" + annotation);
                }
            }

            for (Method method : loadClass.getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '"
                                    + method + "' : " + anno);
                        }
                        MyAnnotation methodAnno = method.getAnnotation(MyAnnotation.class);
                        if (methodAnno.product().equals("001")) {
                            System.out.println("Method with product is 001 = "+ method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
