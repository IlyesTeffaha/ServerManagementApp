package com.example.server.service;

import com.example.server.models.Server;
import com.example.server.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

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
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Getting server by id:{}",id);
        return serverRepo.findById(id).get();

    }

    @Override
    public Server update(Server server) {
        log.info("updating  server:{}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting  server:{}",id);
serverRepo.deleteById(id);
        return Boolean.TRUE;
    }


    private String setServerImageUrl(){
        String[] imagenames={"server1.png","server2.png","server3.png","server4.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path(("/server/image/"+ imagenames[new Random().nextInt( 4)])).toUriString();
    }
}
