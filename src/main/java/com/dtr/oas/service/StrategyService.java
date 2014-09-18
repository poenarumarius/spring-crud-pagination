package com.dtr.oas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtr.oas.exception.DuplicateStrategyException;
import com.dtr.oas.exception.StrategyNotFoundException;
import com.dtr.oas.model.Strategy;

public interface StrategyService {

    public void addStrategy(Strategy strategy) throws DuplicateStrategyException;

    public Strategy getStrategy(long id) throws StrategyNotFoundException;

    public void updateStrategy(Strategy strategy) throws StrategyNotFoundException, DuplicateStrategyException;

    public void deleteStrategy(long id) throws StrategyNotFoundException;

    public List<Strategy> getStrategys();
    
    public Page<Strategy> getStrategys(Pageable pageable);


}
