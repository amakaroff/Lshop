package com.makarov.lshop.base.service.profile.api;

/**
 * Created by Алексей on 25.12.2016.
 */
public interface Validator {

    boolean phoneValidate(String number);

    boolean emailValidate(String mail);

    boolean loginValidate(String login);

    boolean passwordValidate(String password);

    boolean nameValidate(String name);

    boolean photoValidate(String urlPhoto);
}
