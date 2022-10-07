package com.example.server.repo;

import com.example.server.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server,Long> {


    Server findByIpAddress(String ipAddress);

}
