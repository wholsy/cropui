package com.yueny.cropui.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.cropui.controller.BaseController;
import com.yueny.cropui.service.task.cron.CronExpressionManager;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;

/**
 * 任务控制器
 *
 * @author 袁洋 2015年8月12日 上午10:08:14
 *
 */
@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController {
	/**
	 * 任务后续进度安排
	 *
	 * @param cronExp
	 *            任务cron表达式配置
	 * @return 我们的历史
	 */
	@RequestMapping(value = "/onViewCronPlan.json", method = { RequestMethod.GET })
	@ResponseBody
	public NormalResponse<String> onViewCronPlanAction(@RequestParam("cronExp") final String cronExp) {
		final NormalResponse<String> response = new NormalResponse<>();
		response.setData(CronExpressionManager.parseExpression(cronExp));

		return response;
	}

}
