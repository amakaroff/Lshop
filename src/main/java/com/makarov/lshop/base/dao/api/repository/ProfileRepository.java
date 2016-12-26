package com.makarov.lshop.base.dao.api.repository;

import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {

    ProfileEntity findByLogin(String login);
}
