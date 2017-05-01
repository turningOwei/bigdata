package bigdata.init;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.webbase.domain.SessionParamEnum;
import bigdata.webbase.domain.StatusInfoEntity;
import framework.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.google.gson.Gson;
import pub.permission.domain.SysCode;
import pub.permission.domain.SysCodeItem;
import pub.permission.domain.SysCodeRoleSwitch;
import pub.permission.service.ResourceService;
import pub.sys.service.SysCodeItemService;
import pub.user.domain.User;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private SysCodeItemService sysCodeItemService;

	private Gson gson = new Gson();


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute(
				SessionParamEnum.USER.getValue());
		String path = request.getServletPath();
		SysCodeItem codeItem = sysCodeItemService
				.querySysCodeItemByCodeType(SysCode.ROLE_SWITCH);
		if (codeItem == null
				|| codeItem.getValue()
						.equals(SysCodeRoleSwitch.OPEN.getValue())) {
			return true;
		}
		List<String> resourceStrs = resourceService
				.getResourceStr(user.getId());
		if (!CollectionUtil.sourcesHasPath(resourceStrs, path)) {
			ServletOutputStream out = response.getOutputStream();
			StatusInfoEntity info = new StatusInfoEntity();
			info.setMessage("没有该功能权限,请联系管理员");
			info.setStatusCode(StatusInfoEntity.StatusInfoCodeType.error.getValue());
			String json = gson.toJson(info);
			byte[] b = json.getBytes();
			out.write(b);
			out.close();
			return false;
		}
		return true;
	}


	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===========HandlerInterceptor1 postHandle");
	}


	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===========HandlerInterceptor1 afterCompletion");
	}

}
