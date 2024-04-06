package com.qspiders.busreservation.busrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.qspiders.busreservation.dto.User;
public interface UserRepo extends JpaRepository<User, Integer> {

	// User findByReferralCode(String referralCode);
	User findByUserId(int userId);

	@Query(nativeQuery = true, value = "SELECT * FROM user WHERE referral_code=:referral_code")
	User findByReferralCode(@Param("referral_code") String referralCode);

}
