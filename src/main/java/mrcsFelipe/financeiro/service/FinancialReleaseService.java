package mrcsFelipe.financeiro.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mrcsFelipe.financeiro.entity.FinancialRelease;
import mrcsFelipe.financeiro.repository.FinancialReleaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FinancialReleaseService {

	@Autowired
	private FinancialReleaseRepository financialReleaseRepository;
	
	public List<FinancialRelease> findAllReleaseByUser(String email){
		return financialReleaseRepository.findAllReleaseByUser(email);
	}
	
	public void save(FinancialRelease release){
		this.financialReleaseRepository.save(release);
	}
	
	public List<FinancialRelease> findAllReleaseForLimit(String email, int limit){
		return financialReleaseRepository.findAllReleaseForLimit(email, new PageRequest(0, limit));
	}
	
	public void deleteById(Integer id){
		financialReleaseRepository.delete(id);
	}
	
	public List<FinancialRelease> maxRelease(String email){
		return financialReleaseRepository.greatRelease(email);
	}
	
	public List<FinancialRelease> maxReleaseRevenueOrMinReleaseDeposit(String email, String revenueOrDeposit){
		return financialReleaseRepository.greatReleaseRevenueOrDeposit(email, revenueOrDeposit);
	}
	
	public List<FinancialRelease> minReleaseRevenueOrMaxReleaseDeposit(String email, String revenueOrDeposit){
		return financialReleaseRepository.minReleaseRevenueOrDeposit(email, revenueOrDeposit);
	}
	
	public List<FinancialRelease> maxReleaseDeposit(String email){
		return financialReleaseRepository.greatRelease(email);
	}
	
	public List<FinancialRelease> minRelease(String email){
		return financialReleaseRepository.minRelease(email);
	}
	
	public List<FinancialRelease> findAllReleaseBetweenDate(Date one, Date two, String email){
		return financialReleaseRepository.findAllReleaseBetweenDate(one, two, email);
	}
	
	public BigDecimal findTotalBetweenDate(Date one, Date two, String email){
		return financialReleaseRepository.findTotalBetweenDate(one, two, email);
	}
	
	public List<FinancialRelease> findAllReleaseBetweenDateByAccount(Date one, Date two, String email, Integer idAccount){
		return financialReleaseRepository.findAllReleaseBetweenDateByAccount(one, two, email, idAccount);
	}
	
	public BigDecimal findTotalBetweenDateByAccount(Date one, Date two, String email, Integer idAccount){
		return financialReleaseRepository.findTotalBetweenDateByAccount(one, two, email, idAccount);
	}
	
	public BigDecimal totalReleaseByUser(String email){
		return this.financialReleaseRepository.totalReleaseByUserAndAccount(email);
	}

}
