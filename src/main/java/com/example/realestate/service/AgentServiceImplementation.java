package com.example.realestate.service;

import com.example.realestate.dao.AgentRepository;
import com.example.realestate.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgentServiceImplementation {

    @Autowired
    private AgentRepository agentRepository;

    //The method will basically return a list of all agents to the controller

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    //Save Method for Agents
    public Agent saveAgent(Agent agent) {
        if(agentRepository.existsById(agent.getId()))
            throw new DuplicateKeyException("Agent with id " + agent.getId() + " exists!" );
        return agentRepository.save(agent);
    }

    //Get Agent by Id
    public Agent getAgentById(Long agentId) {
        return agentRepository.findById(agentId).orElseThrow(() -> {
            throw new NoSuchElementException("Agent not found with id : " + agentId);
        });
    }

    //Delete method using agentId
    public void deleteAgent(Long agentId) {
        agentRepository.findById(agentId).ifPresentOrElse((agent) -> {
            agentRepository.delete(agent);
        }, () -> {
            throw new NoSuchElementException("not found");
        });
    }

    public void updateAgent(Long agentId, Agent newAgent) {
        agentRepository.findById(agentId).ifPresentOrElse((oldAgent) -> {
            oldAgent.setEmail(newAgent.getEmail());
            oldAgent.setFirst_name(newAgent.getFirst_name());
            oldAgent.setLast_name(newAgent.getLast_name());
//            oldAgent.setNationalId(newAgent.getNationalId());
            oldAgent.setPhone(newAgent.getPhone());
            agentRepository.save(oldAgent);
        }, () -> {
            throw new NoSuchElementException("The agent with id: " + agentId + " does not exist");
        });
    }
}