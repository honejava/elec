	var zTree;
	var demoIframe;

	var setting = {
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
	};

	zNodes =[
		{ id:1, pId:0, name:"Hunter's Home", url:"http://baby666.cn", target:"_blank"},
		{ id:2, pId:0, name:"zTree in Google", url:"http://code.google.com/p/jquerytree/", target:"_blank"},
		{ id:3, pId:0, name:"zTree 功能演示", open:true},
		{ id:31, pId:3, name:"基本功能演示", open:true},
		{ id:311, pId:31, name:"不兼容 IE6", "url":"standardDemo.html", "target":"testIframe"},
		{ id:311, pId:31, name:"兼容 IE6", "url":"standardDemoForIe6.html", "target":"testIframe"},
		{ id:32, pId:3, name:"check 演示", open:true},
		{ id:321, pId:32, name:"CheckBox演示", "url":"checkboxDemo.html", "target":"testIframe"},
		{ id:321, pId:32, name:"Radio演示", "url":"radioDemo.html", "target":"testIframe"},
		{ id:33, pId:3, name:"异步加载演示", "url":"asyncDemo.html", "target":"testIframe"},
		{ id:34, pId:3, name:"事件演示", "url":"eventDemo.html", "target":"testIframe"},
		{ id:35, pId:3, name:"编辑演示", open:true},
		{ id:351, pId:35, name:"默认编辑", "url":"editDemo.html", "target":"testIframe"},
		{ id:352, pId:35, name:"编辑 & 异步加载 共存", "url":"edit&asyncDemo.html", "target":"testIframe"},
		{ id:36, pId:3, name:"JS操作", "url":"jscontrolDemo.html", "target":"testIframe"},
		{ id:39, pId:3, name:"大数据量演示", open:true},
		{ id:391, pId:39, name:"普通加载", "url":"bigDataDemo_normal.html", "target":"testIframe"},
		{ id:392, pId:39, name:"高级异步加载", "url":"bigDataDemo_super.html", "target":"testIframe"},
		{ id:37, pId:3, name:"高级应用演示", open:true},
		{ id:371, pId:37, name:"两棵树的数据交换", "url":"superDemo_mutilTree.html", "target":"testIframe"},
		{ id:372, pId:37, name:"添加自定义控件", "url":"superDemo_diyBtn.html", "target":"testIframe"},
		{ id:375, pId:37, name:"checkbox & radio 共存", "url":"superDemo_check&radio.html", "target":"testIframe"},
		{ id:373, pId:37, name:"右键菜单", "url":"superDemo_rightMenu.html", "target":"testIframe"},
		{ id:374, pId:37, name:"下拉菜单", "url":"superDemo_dropdownMenu.html", "target":"testIframe"},
		{ id:38, pId:3, name:"皮肤演示", url:"http://baby666.cn/hunter/ztree/skinDemo.html", target:"testIframe"}
	];

	$(document).ready(function(){
		zTree = $("#menuTree").zTree(setting, zNodes);	
	});
