import org.apache.log4j.Logger;

public class SimpleMain {

    private static final Logger logger = Logger.getLogger(SimpleMain.class);

    public static void main(String[] args) {
        for(int i = 0; i < 3; i++) {
            try {
                logger.info("result1: " + divide(i));
                logger.debug("result2: " + divide(i));
                logger.warn("result3: " + divide(i));
                logger.error("result3: " + divide(i));
                logger.fatal("result3: " + divide(i));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static int divide(int x) {
        logger.debug("divide method invoked; 2 / " + x);
        if(x == 0) {
            logger.warn("x = 0; exception may occur");
        }
        return 2 / x;
    }
}