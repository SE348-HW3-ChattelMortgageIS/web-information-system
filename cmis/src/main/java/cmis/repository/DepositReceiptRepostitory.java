package cmis.repository;

import cmis.entity.DepositReceipt;
import cmis.entity.SteelRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DepositReceiptRepostitory extends JpaRepository<DepositReceipt, Integer>{
    DepositReceipt findByReceiptId(Integer id);
    DepositReceipt findBySteelRoll(SteelRoll steelRoll);
    @Transactional
    @Modifying
    @Query(value = "update  deposit_receipt set receipt_state = 4 where receipt_id= ?1 ",nativeQuery = true)
    int abnormal(int id);
}
