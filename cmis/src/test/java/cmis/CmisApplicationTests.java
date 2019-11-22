package cmis;

import cmis.service.getMessageService;
import cmis.dto.GeneralMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmisApplicationTests {
	@Autowired
	getMessageService getMessageService;

	@Test
	public void contextLoads() {
		GeneralMessage res = getMessageService.getStatus("1");
		System.out.println(res.toString());
		System.out.println(res.getEntity());
	}

}
