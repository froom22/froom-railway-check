//package com.org.froom.repository;
//
//import com.org.froom.entity.FroomOrderDetails;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FroomOrderDetailsRepository extends JpaRepository<FroomOrderDetails, Long> {
//    @Query(value = "SELECT * FROM froom_poc.froom_order_details where froom_order_id =:uuid",nativeQuery = true)
//    public List<FroomOrderDetails> fetchDetailsByFroomId(Long uuid);
//}
