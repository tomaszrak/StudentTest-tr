package com.prz.testing.handler;

import com.prz.testing.controller.UserData;
import com.prz.testing.exception.InternalServerError;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by ROLO on 18.02.2016.
 */
@ControllerAdvice
public class ExceptionHandlerUtil {

    private Logger logger = Logger.getLogger(ExceptionHandlerUtil.class);

    private UserData userData;

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Void> hadleSQLException(HttpServletRequest rq, Exception e) {
        logger.error(String.format("userId=%d, url=%s", userData.getId(), rq.getRequestURL().toString()), e);
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Void> handleInternalException(HttpServletRequest rq, Exception e){
        logger.error(String.format("userId=%d, url=%s", userData.getId(), rq.getRequestURL().toString()), e);
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
