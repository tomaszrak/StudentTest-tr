package com.prz.testing.util;

import com.prz.testing.controller.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by ROLO on 18.02.2016.
 */
@Component
public class LogUtil {

    @Autowired
    private UserData userData;

    Logger logger = Logger.getLogger(LogUtil.class);

    public void info(String... params) {
        try {
            Long userId = userData.getId();
            StringBuilder sb = new StringBuilder(null != userId ? userId.toString() : "anonymous");
            sb.append(";");
            String className = new Exception().getStackTrace()[1].getClassName();
            sb.append(className.substring(className.lastIndexOf(".") + 1));
            sb.append(";");
            sb.append(new Exception().getStackTrace()[1].getMethodName());
            for (String p : params) {
                sb.append(";");
                sb.append(p);
            }
            logger.info(sb.toString());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
