package pub.permission.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pub.permission.domain.Button;


public interface ButtonDao {

	public Button getButtonById(String buttonId);

	public Integer updateButton(Button button) throws Throwable;

	public Page<Button> queryButtons(PageRequest buildPageRequest);

	public void batchSave(List<Button> buttons);

	public void deleteButtonById(String id);
}
