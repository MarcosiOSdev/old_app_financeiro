package mrcsFelipe.financeiro.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mrcsFelipe.financeiro.entity.FinancialRelease;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface FinancialReleaseRepository extends 
				 CrudRepository<FinancialRelease, Integer>,
				 PagingAndSortingRepository<FinancialRelease, Integer>{

	
	@Modifying
	@Query("SELECT fr FROM FinancialRelease fr, User u WHERE fr.user.id = u.id AND u.email= :email")
	public List<FinancialRelease> findAllReleaseByUser(@Param("email")String email);
	
	
	@Query("SELECT fr FROM FinancialRelease fr, User u "+
		   "WHERE fr.user.id = u.id AND u.email=:email " + 
		   "ORDER BY fr.id DESC")
	public List<FinancialRelease> findAllReleaseForLimit(@Param("email")String email, 
														Pageable pageable);
	
	@Query("FROM FinancialRelease "
			+ "WHERE value=(select max(f.value) from FinancialRelease f WHERE f.user.email=:email) ")
	public List<FinancialRelease> greatRelease(@Param("email") String email);
	
	@Query("FROM FinancialRelease "
			+ "WHERE value=(select max(f.value) from FinancialRelease f"
			               + " WHERE f.typeValue=:revenueOrDeposit"
			               + " AND f.user.email=:email)")
	public List<FinancialRelease> greatReleaseRevenueOrDeposit(@Param("email") String email,
															   @Param("revenueOrDeposit")String revenueOrDeposit);
	
	
	@Query("FROM FinancialRelease "
			+ "WHERE value=(select min(f.value) from FinancialRelease f WHERE f.user.email=:email) ")
	public List<FinancialRelease> minRelease(@Param("email") String email);
	
	
	@Query("FROM FinancialRelease "
			+ "WHERE value=(select min(f.value) from FinancialRelease f "
							+ " WHERE f.user.email=:email AND f.typeValue=:revenueOrDeposit) ")
	public List<FinancialRelease> minReleaseRevenueOrDeposit(@Param("email") String email,
															 @Param("revenueOrDeposit")String revenueOrDeposit);
	
	@Query("FROM FinancialRelease f "
			+ "WHERE  f.dateRelease "
			+ "BETWEEN :dateFirst AND :dateSecond"
			+ " AND f.user.email=:email"
			+ " ORDER BY f.dateRelease ASC")
	public List<FinancialRelease> findAllReleaseBetweenDate(@Param("dateFirst")Date dateFirst, 
															@Param("dateSecond")Date dateSecond,
															@Param("email")String email);
	
	
	
	@Query("SELECT SUM(f.value) FROM FinancialRelease f "
			+ "WHERE  f.dateRelease "
			+ "BETWEEN :dateFirst AND :dateSecond"
			+ " AND f.user.email=:email")
	public BigDecimal findTotalBetweenDate(@Param("dateFirst")Date dateFirst, 
										   @Param("dateSecond")Date dateSecond,
										   @Param("email")String email);
	
	
	@Query("SELECT SUM(f.value) FROM FinancialRelease f"
			+ " WHERE f.user.email=:email")
	public BigDecimal totalReleaseByUserAndAccount(@Param("email")String email);
	
	
	

	@Query("SELECT SUM(f.value) FROM FinancialRelease f "
			+ "WHERE  f.dateRelease "
			+ "BETWEEN :dateFirst AND :dateSecond"
			+ " AND f.user.email=:email"
			+ " AND f.account.id=:idAccount")
	public BigDecimal findTotalBetweenDateByAccount(@Param("dateFirst")Date dateFirst, 
										   @Param("dateSecond")Date dateSecond,
										   @Param("email")String email,
										   @Param("idAccount")Integer idAccount);
	

	@Query("FROM FinancialRelease f "
			+ "WHERE  f.dateRelease "
			+ "BETWEEN :dateFirst AND :dateSecond"
			+ " AND f.user.email=:email"
			+ " AND f.account.id=:idAccount"
			+ " ORDER BY f.dateRelease ASC")
	public List<FinancialRelease> findAllReleaseBetweenDateByAccount(@Param("dateFirst")Date dateFirst, 
															@Param("dateSecond")Date dateSecond,
															@Param("email")String email,
															@Param("idAccount")Integer idAccount);
	
}
