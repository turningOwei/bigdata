package pub.permission.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pub.permission.dao.ButtonDao;
import pub.permission.dao.mongodb.repositories.ButtonRepository;
import pub.permission.domain.Button;

@Repository
public class MongodbButtonDao implements ButtonDao {
	@Autowired
	private ButtonRepository repository;


	public Button getButtonById(String buttonId) {
		return repository.findOne(buttonId);
	}


	public Integer updateButton(Button button) throws Throwable {
		repository.save(button);
		return 1;
	}


	public Page<Button> queryButtons(PageRequest buildPageRequest) {
		return repository.findAll(buildPageRequest);
	}


	public void batchSave(List<Button> buttons) {
		repository.save(buttons);
	}


	public void deleteButtonById(String id) {
		repository.delete(id);
	}

}
