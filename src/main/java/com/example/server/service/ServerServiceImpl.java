package com.example.server.service;

import com.example.server.models.Server;
import com.example.server.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import static com.example.server.enumeration.Status.*;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements  ServerService{

private final ServerRepo serverRepo;



    @Override
    public Server create(Server server) {
        log.info("saving new server:{}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server IP:{}",ipAddress);
        Server server=serverRepo.findByIpAddress(ipAddress);
       /* What is the use of INET address?
                InetAddress class provides methods to get the IP address of any hostname.*/
        InetAddress address=InetAddress.getByName(ipAddress);
       server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);

serverRepo.save(server);
        return server;
     }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


    private String setServerImageUrl(){
        return  null;
    }
}
