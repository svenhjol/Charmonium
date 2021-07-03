package svenhjol.charmonium.init;

import org.apache.logging.log4j.LogManager;
import svenhjol.charmonium.helper.LogHelper;

public class CharmoniumLog {
    public static void init() {
        LogHelper.INSTANCE = LogManager.getFormatterLogger("Charmonium");
    }
}
