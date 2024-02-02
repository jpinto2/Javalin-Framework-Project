package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }
    
    public Account register(Account account) {
        return accountDAO.createAccount(account);
    }

    public Account login(Account account) {
        return accountDAO.login(account);
    }
}
