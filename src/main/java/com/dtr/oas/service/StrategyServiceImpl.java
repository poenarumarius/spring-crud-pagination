package com.dtr.oas.service;

import java.util.List;


//import com.dtr.oas.dao.StrategyDAO;
import com.dtr.oas.exception.DuplicateStrategyException;
import com.dtr.oas.exception.StrategyNotFoundException;
import com.dtr.oas.model.Strategy;
import com.dtr.oas.repository.StrategyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StrategyServiceImpl implements StrategyService {

//    @Autowired
//    private StrategyDAO strategyDAO;

    @Autowired
    private StrategyRepository strategyRepository;

    @Override
    public void addStrategy(Strategy strategy) throws DuplicateStrategyException {
//        strategyDAO.addStrategy(strategy);
        strategyRepository.save(strategy);
    }

    @Override
    public void updateStrategy(Strategy strategy) throws StrategyNotFoundException, DuplicateStrategyException{
//        strategyDAO.updateStrategy(strategy);
        strategyRepository.save(strategy);
    }

    @Override
    public Strategy getStrategy(long id) throws StrategyNotFoundException {
//        return strategyDAO.getStrategy(id);
        return strategyRepository.findOne(id);
    }

    @Override
    public void deleteStrategy(long id) throws StrategyNotFoundException {
//        strategyDAO.deleteStrategy(id);
        strategyRepository.delete(id);
    }

    @Override
    public List<Strategy> getStrategys() {
//        return strategyDAO.getStrategies();
        return strategyRepository.findAll();
    }

	@Override
	public Page<Strategy> getStrategys(Pageable pageable) {
		return strategyRepository.findAll(pageable);
	}

}
