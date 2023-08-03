package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** The base class of the controller class */
public class BaseController {

    /** Operation successful status code */
    public static final int OK = 200;


    /**
     * Get uid from HttpSession object
     * @param session HttpSession object
     * @return the id of the currently logged in user
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }


    /**
     * Get username from HttpSession object
     * @param session HttpSession object
     * @return The currently logged in username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }


    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("Username is already taken.");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("User data does not exist.");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("Password is incorrect.");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("Address count limit reached.");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("Address not found.");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("Access denied.");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("Product not found.");
        } else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("Cart not found.");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("An error occurred while inserting data.");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("An error occurred while updating data.");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMessage("An error occurred while deleting data.");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("The uploaded file is empty.");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("The uploaded file size exceeds the limit.");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("This file type is not supported.");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("The file state is abnormal, it may have been moved or deleted.");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("Read and write errors occurred during file upload, please try again later.");
        }

        return result;
    }
}





