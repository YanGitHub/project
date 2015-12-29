package kj.pos.controller.util;

import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * Created by Yan on 15-12-4.
 */
public class ID {
    private static final Logger logger = Logger.getLogger(ID.class);

    public static String getGuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
