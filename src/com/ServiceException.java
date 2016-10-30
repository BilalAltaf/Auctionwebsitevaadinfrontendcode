package com;

/**
 * Created by bilal on 10/15/2016.
 */

import java.util.concurrent.ExecutionException;


public class ServiceException extends ExecutionException {
    public ServiceException(String message) {
        super(message);
    }
}
