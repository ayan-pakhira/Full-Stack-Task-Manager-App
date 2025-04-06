package com.example.taskmanagerapp.Task.Manager.App.Repositories;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<UserDetails, ObjectId> {
}
