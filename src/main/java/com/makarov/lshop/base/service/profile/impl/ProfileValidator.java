package com.makarov.lshop.base.service.profile.impl;

import com.makarov.lshop.base.service.profile.api.Validator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileValidator implements Validator {

    @Override
    public boolean phoneValidate(String number) {
        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    @Override
    public boolean emailValidate(String mail) {
        Pattern pattern = Pattern.compile("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}"
                + "~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|"
                + "edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    @Override
    public boolean loginValidate(String login) {
        return !(login.contains(" ") || login.length() < 4 || login.length() > 20);
    }

    @Override
    public boolean passwordValidate(String password) {
        return !(password.contains(" ") || password.length() < 6 || password.length() > 20);
    }

    @Override
    public boolean nameValidate(String name) {
        return !(name.length() < 3 || name.length() > 20);
    }

    @Override
    public boolean photoValidate(String urlPhoto) {
        Pattern pattern = Pattern.compile("^(.*\\.(?:png|jpg))$");
        Matcher matcher = pattern.matcher(urlPhoto);
        return matcher.matches();
    }
}
