package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        messageService = new MessageService();
        accountService = new AccountService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
       app.post("/register", this::registerHandler);
       app.post("/login", this::loginHandler);

       app.post("/messages", this::createMessageHandler);
       app.get("/messages", this::getAllMessagesHandler);
       app.get("/messages/{message_id}", this::getMessageHandler);
       app.delete("/messages/{message_id}", this::deleteMessageHandler);
       app.patch("/messages/{message_id}", this::updateMessageHandler);
       app.get("accounts/{account_id}/messages", this::getUserMessagesHandler);
       
       return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //    context.json("sample text");
    // }
    private void registerHandler(Context context) throws JsonProcessingException {

        ObjectMapper objMapper = new ObjectMapper();
        Account account = objMapper.readValue(context.body(), Account.class);

        Account register = accountService.register(account);
       
        if (register != null) {
            context.json(objMapper.writeValueAsString(register));
        }else{
            context.status(400);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException {

        ObjectMapper objMapper = new ObjectMapper();
        Account account = objMapper.readValue(context.body(), Account.class);

        Account login = accountService.login(account);
       
        if (login != null) {
            context.json(objMapper.writeValueAsString(login));
        }else{
            context.status(401);
        }
    }

    private void createMessageHandler(Context context) throws JsonProcessingException {

        ObjectMapper objMapper = new ObjectMapper();
        Message message = objMapper.readValue(context.body(), Message.class);

        Message create = messageService.createMessage(message);
       
        if (create != null) {
            context.json(objMapper.writeValueAsString(create));
        }else{
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    private void getUserMessagesHandler(Context context) {
        int posted_by = Integer.parseInt(context.pathParam("account_id"));
        context.json(messageService.getUserMessages(posted_by));
    }

    

}