package com.example.taskmanagerapp.Task.Manager.App.Repositories;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, ObjectId> {

    UserDetails findByUserName(String userName);
}
