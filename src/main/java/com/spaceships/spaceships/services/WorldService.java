package com.spaceships.spaceships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spaceships.spaceships.models.World;

@Service
public class WorldService {
    
    private WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    public List<World> getWorlds() {
        return worldRepository.findAll();
    }

    @SuppressWarnings("null")
    public World addWorld(World world) {
        return worldRepository.insert(world);
    }

    public World getWorldByName(String name) {
        return worldRepository.findByWorldNameLikeIgnoreCase(name);
    }

    public World getWorldById(String id) {

        @SuppressWarnings("null")
        Optional<World> world = worldRepository.findById(id);

        if (world.isPresent()) {
            return world.get();
        } else {
            return null; 
        }       
    }

    public World editWorld(String id, World world) {
        worldRepository.findAndSetNameById(id, world.getWorldName());
        @SuppressWarnings("null")
        Optional<World> newWorld = worldRepository.findById(id);

        return newWorld.get();
    }

    @SuppressWarnings("null")
    public void deleteWorld(String id) {
        worldRepository.deleteById(id);
    }
}
