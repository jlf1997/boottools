package com.cimr.api.dev.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cimr.api.dev.model.Terminal;



public interface TerminalJpa extends JpaRepository<Terminal, Long>,JpaSpecificationExecutor<Terminal>{

}
