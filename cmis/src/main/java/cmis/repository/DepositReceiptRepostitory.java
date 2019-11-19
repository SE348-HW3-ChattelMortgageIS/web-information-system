package cmis.repository;

import cmis.entity.DepositReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositReceiptRepostitory extends JpaRepository<DepositReceipt, Integer> {

}
