package cn.haut.elec.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecRepair;
import cn.haut.elec.service.IElecFileUploadService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ValueStackUtils;

@Controller("elecFileUploadAction")
@Scope(value = "prototype")
public class ElecFileUploadAction extends BaseAction<ElecFileUpload> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入service
	@Resource(name = IElecFileUploadService.SERVICE_NAME)
	private IElecFileUploadService fileUploadService;

	/**
	 * @Name: detail
	 * @Description: 查看站点解决的详细方法
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String detail() {
		// 根据bugID来获取到bug的信息
		if (this.getModel().getElecBug() != null) {
			ElecBug bug = fileUploadService.findBugById(this.getModel()
					.getElecBug().getBugID());
			request.setAttribute("bug", bug);
		}
		// 查询到当前bug的所有的上传文件
		List<ElecFileUpload> fileUploadList = fileUploadService
				.findFileUploadByCondition();
		request.setAttribute("fileUploadList", fileUploadList);
		return "detail";
	}

	/**
	 * @Name: upload
	 * @Description: 查看站点解决的详细方法
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String upload() {
		// 获取根目录下的upload路径
		String realPath = request.getServletContext().getRealPath("/upload");
		ElecFileUpload upload = this.getModel();
		try {
			if (upload.getFiles() != null && upload.getFiles().length > 0) {
				// 获取bug数据
				ElecBug bug = fileUploadService.findBugById(this.getModel()
						.getElecBug().getBugID());
				for (int i = 0; i < upload.getFiles().length; i++) {
					upload.setElecBug(bug);
					upload.setFile(upload.getFiles()[i]);
					upload.setFileFileName(upload.getFilesFileName()[i]);
					upload.setFileContentType(upload.getFilesContentType()[i]);
					// 保存上传文件的数据
					fileUploadService.save(upload);

					File destFile = new File(realPath,
							upload.getFilesFileName()[i]);
					FileUtils.copyFile(upload.getFiles()[i], destFile);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("文件上传出现错误", e);
		}
		return detail();
	}

	/**
	 * @Name: view
	 * @Description: 查看上传文件
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-28（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String view() {
		ElecFileUpload upload = fileUploadService.findFileUploadById(this
				.getModel().getFileUploadID());
		try {
			upload.setTargetName(request.getServletContext()
					.getResourceAsStream("/upload/" + upload.getFileFileName()));
			upload.setContentType(upload.getFileContentType());
			upload.setFilename(upload.getFileFileName());
			// System.out.println(upload.getTargetName());
		} catch (Exception e) {
			throw new RuntimeException("文件下载出现错误", e);
		}
		ValueStackUtils.setValueStatck(upload);
		return "view";
	}

	/**
	 * @Name: delete
	 * @Description: 删除上传的文件
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-28（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunFile.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String delete() {
		ElecBug bug = this.getModel().getElecBug();
		fileUploadService.deleteBugFileUploadById(this.getModel()
				.getFileUploadID());

		this.getModel().setElecBug(bug);
		return detail();
	}

	/**
	 * 站点信息的上传 进入到 站点维护的上传页面
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String up() {
		if (this.getModel().getElecPlan() != null
				&& this.getModel().getElecPlan().getPlanID() != null) {
			ElecPlan plan = fileUploadService.findPlanById(this.getModel()
					.getElecPlan().getPlanID());
			request.setAttribute("plan", plan);
		}
		List<ElecFileUpload> fileUploadList = fileUploadService
				.findFileUploadListByCondition(this.getModel());
		request.setAttribute("fileUploadList", fileUploadList);
		return "up";
	}

	@AnnotationLimit(mid = "ai", pid = "ag")
	public String load() {
		// 获取根目录下的upload路径
		String realPath = request.getServletContext()
				.getRealPath("/planUpload");
		ElecFileUpload upload = this.getModel();
		try {
			if (upload.getFiles() != null && upload.getFiles().length > 0) {
				// 获取plan数据
				ElecPlan plan = fileUploadService.findPlanById(upload
						.getElecPlan().getPlanID());
				for (int i = 0; i < upload.getFiles().length; i++) {
					upload.setElecPlan(plan);
					upload.setFile(upload.getFiles()[i]);
					upload.setFileFileName(upload.getFilesFileName()[i]);
					upload.setFileContentType(upload.getFilesContentType()[i]);
					// 保存上传文件的数据
					fileUploadService.save(upload);

					File destFile = new File(realPath,
							upload.getFilesFileName()[i]);
					FileUtils.copyFile(upload.getFiles()[i], destFile);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("文件上传出现错误", e);
		}
		return up();
	}

	@AnnotationLimit(mid = "ai", pid = "ag")
	public String del() {
		ElecPlan plan = this.getModel().getElecPlan();
		fileUploadService.deletePlanFileUploadById(this.getModel()
				.getFileUploadID());
		this.getModel().setElecPlan(plan);
		return up();
	}

	@AnnotationLimit(mid = "ai", pid = "ag")
	public String vw() {
		ElecFileUpload upload = fileUploadService.findFileUploadById(this
				.getModel().getFileUploadID());
		try {
			upload.setTargetName(request.getServletContext()
					.getResourceAsStream(
							"/planUpload/" + upload.getFileFileName()));
			upload.setContentType(upload.getFileContentType());
			upload.setFilename(upload.getFileFileName());
			// System.out.println(upload.getTargetName());
		} catch (Exception e) {
			throw new RuntimeException("文件下载出现错误", e);
		}
		ValueStackUtils.setValueStatck(upload);
		return "view";
	}

	/***
	 * 设备校准记录的上传管理
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustFile() {
		// 获取到校准上传的文件
		List<ElecFileUpload> fileUploadList = fileUploadService
				.findAdjustFileUploadByCondition(this.getModel().getAdjustID());
		request.setAttribute("fileUploadList", fileUploadList);
		// 获取到设备校准
		ElecAdjust adjust = fileUploadService.finAdjustById(this.getModel()
				.getAdjustID());
		request.setAttribute("adjust", adjust);
		return "adjustFile";
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustUpload() {
		// 获取根目录下的upload路径
		String realPath = request.getServletContext().getRealPath(
				"/adjustUpload");
		ElecFileUpload upload = this.getModel();
		try {
			if (upload.getFiles() != null && upload.getFiles().length > 0) {
				// 获取bug数据
				for (int i = 0; i < upload.getFiles().length; i++) {
					upload.setAdjustID(upload.getAdjustID());
					upload.setFile(upload.getFiles()[i]);
					upload.setFileFileName(upload.getFilesFileName()[i]);
					upload.setFileContentType(upload.getFilesContentType()[i]);
					// 保存上传文件的数据
					fileUploadService.save(upload);

					File destFile = new File(realPath,
							upload.getFilesFileName()[i]);
					FileUtils.copyFile(upload.getFiles()[i], destFile);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("文件上传出现错误", e);
		}
		return adjustFile();
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustDownload() {
		ElecFileUpload upload = fileUploadService.findFileUploadById(this
				.getModel().getFileUploadID());
		try {
			upload.setTargetName(request.getServletContext()
					.getResourceAsStream(
							"/adjustUpload/" + upload.getFileFileName()));
			upload.setContentType(upload.getFileContentType());
			upload.setFilename(upload.getFileFileName());
		} catch (Exception e) {
			throw new RuntimeException("文件下载出现错误", e);
		}
		ValueStackUtils.setValueStatck(upload);
		return "view";
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustDelete() {
		Integer adjustID = fileUploadService.findFileUploadById(
				this.getModel().getFileUploadID()).getAdjustID();
		fileUploadService.deleteAdjustFileUploadById(this.getModel()
				.getFileUploadID());
		this.getModel().setAdjustID(adjustID);
		return adjustFile();
	}

	/**
	 * 设备检修的文件上传
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairFile() {
		// 获取到检修上传的文件
		List<ElecFileUpload> fileUploadList = fileUploadService
				.findRepairFileUploadByCondition(this.getModel().getRepairID());
		request.setAttribute("fileUploadList", fileUploadList);
		// 获取到设备校准
		ElecRepair repair = fileUploadService.finRepairById(this.getModel()
				.getRepairID());
		request.setAttribute("repair", repair);
		return "repairFile";
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairUpload() {
		// 获取根目录下的upload路径
		String realPath = request.getServletContext().getRealPath(
				"/repairUpload/");
		ElecFileUpload upload = this.getModel();
		try {
			if (upload.getFiles() != null && upload.getFiles().length > 0) {
				// 获取bug数据
				for (int i = 0; i < upload.getFiles().length; i++) {
					upload.setRepairID(upload.getRepairID());
					upload.setFile(upload.getFiles()[i]);
					upload.setFileFileName(upload.getFilesFileName()[i]);
					upload.setFileContentType(upload.getFilesContentType()[i]);
					// 保存上传文件的数据
					fileUploadService.save(upload);

					File destFile = new File(realPath,
							upload.getFilesFileName()[i]);
					FileUtils.copyFile(upload.getFiles()[i], destFile);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("文件上传出现错误", e);
		}
		return repairFile();
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairDownload() {
		ElecFileUpload upload = fileUploadService.findFileUploadById(this
				.getModel().getFileUploadID());
		try {
			upload.setTargetName(request.getServletContext()
					.getResourceAsStream(
							"/repairUpload/" + upload.getFileFileName()));
			upload.setContentType(upload.getFileContentType());
			upload.setFilename(upload.getFileFileName());
		} catch (Exception e) {
			throw new RuntimeException("文件下载出现错误", e);
		}
		ValueStackUtils.setValueStatck(upload);
		return "view";
	}

	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairDelete() {
		Integer repairID = fileUploadService.findFileUploadById(
				this.getModel().getFileUploadID()).getRepairID();
		fileUploadService.deleteRepairFileUploadById(this.getModel()
				.getFileUploadID());
		this.getModel().setRepairID(repairID);
		return repairFile();
	}

}
