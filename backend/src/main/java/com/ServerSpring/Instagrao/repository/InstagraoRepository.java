package com.ServerSpring.Instagrao.repository;

import com.ServerSpring.Instagrao.models.Instagrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



public interface InstagraoRepository extends MongoRepository<Instagrao, String> {

}
