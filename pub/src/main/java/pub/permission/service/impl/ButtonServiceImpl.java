package pub.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import pub.permission.dao.ButtonDao;
import pub.permission.domain.Button;
import pub.permission.service.ButtonService;

public class ButtonServiceImpl implements ButtonService {
	@Autowired
	private ButtonDao buttonDao;


	public Button getButtonById(String buttonId) {
		if (StringUtils.isEmpty(buttonId))
			return null;
		return buttonDao.getButtonById(buttonId);
	}


	public void updateButton(Button button) throws Throwable {
		buttonDao.updateButton(button);
	}


	public Page<Button> queryButtons(PageRequest buildPageRequest) {
		return buttonDao.queryButtons(buildPageRequest);
	}


	public void deleteButtonById(String id) {
		buttonDao.deleteButtonById(id);
	}
}
