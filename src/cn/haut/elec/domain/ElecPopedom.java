package cn.haut.elec.domain;

@SuppressWarnings("serial")
public class ElecPopedom implements java.io.Serializable {

	private String mid; // 权限Code（主键ID）
	private String pid; // 父级权限code，如果已经是根节点则为0
	private String name; // 权限名称
	private String url; // 权限在系统中执行访问地址的URL
	private String icon; // 如果是菜单，则为显示图片的URL
	private String target; // 如果是菜单，链接执行的Frame区域名称
	private boolean isParent; // 是否是父节点，父节点为true，子节点为false
	private boolean isMenu; // 是否是系统菜单结构

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	/**
	 * 判断当前权限是否被选中 如果选中了就为true 如果没有被选中 那么急为false
	 */
	private boolean flag;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
