package at.cgsit.train.java.system_vars;

import java.util.Properties;

public class GetJvmSysPropertiesMain {

    static void main(String[] args) {

        // properties not from evnironement but from JVM System itself
        Properties props = System.getProperties();

        for (String key : props.stringPropertyNames()) {
            System.out.println(key + " = " + props.getProperty(key));
        }
    }

}
