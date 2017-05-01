package pub.permission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import pub.permission.domain.Button;

public interface ButtonService {

	public Button getButtonById(String buttonId);

	public void updateButton(Button button) throws Throwable;

	public Page<Button> queryButtons(PageRequest buildPageRequest);

	public void deleteButtonById(String id);
}
