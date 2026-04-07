package com.serfinsa.backend.config;

import com.serfinsa.backend.seeder.DataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeederConfig implements CommandLineRunner {

    @Autowired
    private List<DataSeeder> seeder;

    @Override
    public void run(String... args){
        seeder.forEach(DataSeeder::seed);
    }
}