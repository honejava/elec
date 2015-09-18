var menu = {
	setting : {
		isSimpleData : true,
		treeNodeKey : "mid",
		treeNodeParentKey : "pid",
		showLine : true,
		root : {
			isRoot : true,
			nodes : []
		}
	},

	loadMenuTree : function() { // 获取到要加载的数据【显示什么功能模块】
		$.post("elecMenuAction_showMenu.do", {
			"jctID" : "123"
		}, function(data) {
			$("#menuTree").zTree(menu.setting, data);
		});
	}

/*
 * loadMenuTree : function() { $("#menuTree").zTree(menu.setting,
 * privilegeDate); }
 */
};

$().ready(function() {
	menu.loadMenuTree();
});
