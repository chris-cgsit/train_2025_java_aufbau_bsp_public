package at.cgsit.train.java.system_vars;

public class InputParamMain {

    // java -cp myjar.jar InputParamMain test 123 ix
    public static void main(String[] args) {

        String arg1 = (args.length > 0) ? args[0] : "default1";
        String arg2 = (args.length > 1) ? args[1] : "default2";
        String arg3 = (args.length > 2) ? args[2] : "default3";

        Integer.parseInt(arg2);

        System.out.println("arg1 = " + arg1);
        System.out.println("arg2 = " + arg2);
        System.out.println("arg3 = " + arg3);
    }
}
