package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public Message createMessage(Message message) {
        return messageDAO.createMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessage(int message_id){
        return messageDAO.getMessage(message_id);
    }

    public Message deleteMessage(int message_id){
        return messageDAO.deleteMessage(message_id);
    }

    public Message updateMessage(int message_id,  String message_text){
        return messageDAO.updateMessage(message_id, message_text);
    }
    
     public List<Message> getUserMessages(int posted_by){
        return messageDAO.getUserMessages(posted_by);
    }

}
