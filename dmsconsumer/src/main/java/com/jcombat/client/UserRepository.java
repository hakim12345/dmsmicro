package com.jcombat.client;

import java.util.List;

public interface UserRepository {

    List<User> getUserList();

    User getUser(String userId);

}
