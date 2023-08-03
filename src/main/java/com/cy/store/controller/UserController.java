package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller class that handles user-related requests
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;


    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        // Call the business object to perform registration
        userService.reg(user);

        // return
        return new JsonResult<Void>(OK);
    }

    /*
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        // create return value
        JsonResult<Void> result = new JsonResult<Void>();
        try {
            // Call the business object to perform registration
            userService.reg(user);
            // successful response
            result. setState(200);
        } catch (UsernameDuplicateException e) {
            // username is taken
            result. setState(4000);
            result.setMessage("The username is already taken");
        } catch (InsertException e) {
            // insert data exception
            result. setState(5000);
            result.setMessage("Registration failed, please contact the system administrator");
        }
        return result;
    }
    */


    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        // Call the method of the business object to perform login and get the return value
        User data = userService.login(username, password);

        // After successful login, store uid and username in HttpSession
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        // System.out.println("uid in Session=" + getUidFromSession(session));
        // System.out.println("username in Session=" + getUsernameFromSession(session));

        // Encapsulate the above return value and status code OK into the response result and return
        return new JsonResult<User>(OK, data);
    }


    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        // Call session.getAttribute("") to get uid and username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // Call the business object to modify the password
        userService.changePassword(uid, username, oldPassword, newPassword);

        // return success
        return new JsonResult<Void>(OK);
    }


    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        // Get uid from HttpSession object
        Integer uid = getUidFromSession(session);

        // Call the business object to execute and get the data
        User data = userService.getByUid(uid);

        // response success and data
        return new JsonResult<User>(OK, data);
    }


    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // Get uid and username from the HttpSession object
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // Call the business object to modify user data
        userService.changeInfo(uid, username, user);

        // successful response
        return new JsonResult<Void>(OK);
    }


    /**
     * The upper limit of the avatar file size (10MB)
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;


    /**
     * Allowed uploaded avatar file types
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();


    /** Initialize the file types of avatars allowed to be uploaded */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }


    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(MultipartFile file, HttpSession session) {

           // Check if the uploaded file is empty
            if (file. isEmpty()) {
                // yes: throw an exception
                throw new FileEmptyException("The uploaded avatar file is not allowed to be empty");
            }

            // Determine whether the uploaded file size exceeds the limit
            if (file.getSize() > AVATAR_MAX_SIZE) { // getSize(): returns the size of the file in bytes
                // yes: throw an exception
                throw new FileSizeException("Not allowed to upload more than " + (AVATAR_MAX_SIZE / 1024) + "KB avatar file");
            }

            // Determine whether the uploaded file type exceeds the limit
            String contentType = file. getContentType();
            // boolean contains(Object o): If the current list contains an element, the return result is true; if it does not contain the element, the return result is false
            if (!AVATAR_TYPES. contains(contentType)) {
                // yes: throw an exception
                throw new FileTypeException("This type of file is not supported as an avatar, allowed file types: " + AVATAR_TYPES);
            }

            // Get the absolute disk path of the current project
            String parent = session.getServletContext().getRealPath("upload");
            System.out.println(parent);
            // Folder to save avatar files
            File dir = new File(parent);
            if (!dir. exists()) {
                dir.mkdirs();
            }

            // The file name of the saved avatar file
            String suffix = "";
            String originalFilename = file. getOriginalFilename();
            int beginIndex = originalFilename. lastIndexOf(".");
            if (beginIndex > 0) {
                suffix = originalFilename. substring(beginIndex);
            }
            String filename = UUID.randomUUID().toString() + suffix;

            // Create a file object, representing the saved avatar file
            File dest = new File(dir, filename);
            // Execute and save the avatar file
            try {
                file. transferTo(dest);
            } catch (IllegalStateException e) {
                // Throw an exception
                throw new FileStateException("The file state is abnormal, the file may have been moved or deleted");
            } catch (IOException e) {
                // Throw an exception
                throw new FileUploadIOException("Read and write errors when uploading files, please try again later");
            }

            // avatar path
            String avatar = "/upload/" + filename;
            // Get uid and username from Session
            Integer uid = getUidFromSession(session);
            String username = getUsernameFromSession(session);
            // write the avatar to the database
            userService. changeAvatar(uid, username, avatar);

            // Return the successful avatar path
            return new JsonResult<String>(OK, avatar);
        }

}