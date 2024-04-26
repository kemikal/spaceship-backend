package com.spaceships.spaceships.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.spaceships.spaceships.models.Spaceship;

@Service
public class SpaceshipService {
    private final MongoOperations mongoOperations;

    public SpaceshipService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @SuppressWarnings("null")
    public Spaceship addSpaceship(Spaceship spaceship) {
        return mongoOperations.insert(spaceship);
    }

    public List<Spaceship> getSpaceships() {
        return mongoOperations.findAll(Spaceship.class);
    }

    public List<Spaceship> getSpaceship(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("world").is(id));
        
        return mongoOperations.find(query, Spaceship.class);
    }

    // @SuppressWarnings("null")
    // public Spaceship editSpaceship(String id, Spaceship spaceship) {
    //     Query query = Query.query(Criteria.where("id").is(id));
    //     Update update = Update.update("captain", spaceship.getCaptain());

    //     mongoOperations.updateFirst(query, update, Spaceship.class);
    //     return mongoOperations.findById(id, Spaceship.class);
    // }

    public Spaceship editSpaceship(String id, Spaceship spaceship) {
        spaceship.setId(id);
        return mongoOperations.save(spaceship);
    }

   public void deleteSpaceship(String id) {
    Query query = Query.query(Criteria.where("id").is(id));
    mongoOperations.remove(query, Spaceship.class);
   }

}
