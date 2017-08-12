Ext.onReady(function() {
	var pageSize = 8;
	Ext.QuickTips.init();
	// 创建person类
	Ext.define("person", {
		extend : "Ext.data.Model",
		fields : [ {
			name : 'id',
			type : 'int',
			sortable : true
		}, {
			name : 'username',
			type : 'string',
			sortable : true
		}, {
			name : 'sex',
			type : 'string',
			sortable : true
		}, {
			name : 'birthday',
			type : 'date',
			sortable : true
		}, {
			name : 'salary',
			type : 'string',
			sortable : true
		}, {
			name : 'account',
			type : 'string',
			sortable : true
		}, {
			name : 'email',
			type : 'string',
			sortable : true
		} ]
	});
	// 创建store数据集合
	var store = Ext.create("Ext.data.Store", {
		model : 'person',
		storeId : 'store',
		pageSize : pageSize,
		proxy : {
			type : 'ajax',
			url : 'rest/server/person/queryall',
			actionMethods : 'post',
			reader : {
				type : 'json',
				root : 'items',
				id : 'id',
				totalProperty : 'total'
			},
			writer : {
				type : 'json'
			}
		},
		autoLoad : true
	});
	store.load({
		params : {// 这两个参数是分页的关键，当你点击 下一页 时，这里的值会传到后台,也就是会重新运行：store.load
			start : 0,
			limit : pageSize
		}
	});
	// 创建grid面板
	var grid = Ext.create("Ext.grid.Panel", {
		frame : true,// 渲染
		columns : [// 列模式的集合
		{
			text : "编号",
			dataIndex : 'id',
			width : 100
		}, {
			text : "姓名",
			dataIndex : 'username',
			width : 100
		}, {
			text : "性别",
			dataIndex : 'sex',
			width : 100
		}, {
			text : "生日",
			dataIndex : 'birthday',
			width : 100,
			renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')
		}, {
			text : "薪水",
			dataIndex : 'salary',
			width : 100
		}, {
			text : "余额",
			dataIndex : 'account',
			width : 100
		}, {
			text : "邮箱",
			dataIndex : 'email',
			width : 180,
			field : {
				xtype : 'textfield',
				allowBlank : false
			}
		} ],
		tbar : [ {
			xtype : 'button',
			text : '添加',
			handler : function() {
				addPerson();
			}
		}, {
			xtype : 'button',
			text : '修改',
			handler : function(obj) {
				updatePerson(obj);
			}
		}, {
			xtype : 'button',
			text : '删除',
			handler : function(obj) {
				delPerson(obj);
			}
		}, '<font color="green">查找姓名</font>', new Ext.form.TriggerField({
			id : 'keyWord',
			onTriggerClick : searchPerson
		// 指定trigger触发调用函数
		}) ],
		// 加分页栏
		dockedItems : [ {
			xtype : 'pagingtoolbar',
			store : Ext.data.StoreManager.lookup('store'),
			dock : 'bottom',
			displayInfo : true
		} ],
		// 创建单元格编辑插件
		plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 1
		}) ],

		// 加多选框
		selType : 'checkboxmodel',
		multiselect : true,
		store : Ext.data.StoreManager.lookup('store')
	});

	// 创建窗体
	var win = new Ext.Window({
		title : '人员信息管理',
		layout : 'fit',
		width : 830,
		height : 300,
		iconCls : 'gridIcon',
		closeable : false,// 不能关闭窗口
		frame : true,// 圆角边框
		plain : true,
		resizable : false,// 窗体大小不可变
		items : grid
	});
	win.show();
	/*
	 * 删除功能
	 */
	function delPerson(obj) {
		var myGrid = obj.findParentByType("gridpanel");
		var data = myGrid.getSelectionModel().getSelection();
		Ext.Msg.alert(data);
		if (data.length == 0) {
			Ext.Msg.alert("提示", "您最少要选择一条数据");
			return;
		}
		// 当有一条记录被选中
		var st = myGrid.getStore();
		var ids = [];
		Ext.Array.each(data, function(record) {
			ids.push(record.get('id'));
		});
		Ext.Msg.confirm("提示", "记录删除后将不可恢复，是否继续执行此操作？", returnBack);
		function returnBack() {
			Ext.Ajax.request({
				method : 'post',
				url : 'rest/server/person/delete',
				params : {
					ids : ids.join(",")
				},
				// 不管成功与否，都调用此回调函数
				callback : function(options, success, response) {
					Ext.Msg.alert(response.responseText);
					if (success == true) {
						Ext.Msg.alert("提示", "成功删除所选记录！");
						store.reload();
					}
				}
			});
		}
	}
	/*
	 * 添加功能
	 */
	function addPerson() {
		// 首先定义添加处理函数的表单
		var addForm = new Ext.FormPanel({
			id : 'addForm',
			url : 'rest/server/person/add',
			labelAlign : 'right',// 居右对齐
			labelWidth : 50,// 标签宽度
			items : [ {
				xtype : 'textfield',
				fieldLabel : '姓名',
				name : 'username',
				allowBlank : false,
				blankText : '姓名不允许为空',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '性别',
				name : 'sex',
				allowBlank : false,
				blankText : '性别不允许为空',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '邮件',
				name : 'email',
				allowBlank : false,
				blankText : '邮件不允许为空',
				width : 200
			}, {
				xtype : "datefield",
				name : "birthday",
				fieldLabel : "生日",
				editable : true,
				emptyText : "--请选择--",
				format : "Y-m-d",// 日期的格式
				altFormats : "Y/m/d|Ymd",
				width : 180
			}, {
				xtype : 'textfield',
				fieldLabel : '薪水',
				name : 'salary',
				allowBlank : false,
				blankText : '薪水不允许为空',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '余额',
				name : 'account',
				allowBlank : false,
				blankText : '余额不允许为空',
				width : 200
			} ],
			buttons : [ {
				text : '添加',
				handler : function() {
					if (addForm.getForm().isValid()) {// 如果所有的表单验证都通过
						// 表单直接提交
						addForm.getForm().submit({
							waitMsg : '正在保存数据........',
							success : function(form, action) {
								Ext.Msg.alert('提示', '人员信息保存成功');
								Ext.getCmp("addWindow").close();
								store.reload();//
							},
							failure : function(form, action) {
								Ext.Msg.alert('tip', 'error');
							}
						});
					}
				}
			} ]
		});
		var addWindow = new Ext.Window({
			id : 'addWindow',
			title : '添加人员信息',
			layout : 'fit',// 充满整个window
			width : 300,
			height : 260,
			iconCls : 'addIcon',// 窗体样式图标
			modal : true,// 模式窗体
			frame : true,// 圆角边框
			plain : true,
			resizeable : false,
			items : [ addForm ]
		});

		addWindow.show();// 显示窗体addWindow
	}
	/*
	 * 修改
	 */
	function updatePerson(obj) {
		// 没有记录选中的情况下

		var counts = obj.findParentByType("gridpanel").getSelectionModel()
				.getCount();
		if (0 == counts) {
			Ext.Msg.alert("提示", "请选择一条要操作的记录！");
			return;
		}
		// 记录存在 则有：
		var row = obj.findParentByType("gridpanel").getSelectionModel()
				.getSelection();
		var id = row[0].get("id");
		var username = row[0].get("username");
		var sex = row[0].get("sex");
		var email = row[0].get("email");
		var birthday = row[0].get("birthday");
		var salary = row[0].get("salary");
		var account = row[0].get("account");
		var modifyForm = new Ext.FormPanel({
			id : 'modifyForm',
			url : 'rest/server/person/update',
			labelAlign : 'right',
			labelWidth : 50,
			frame : true,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '姓名',
				name : 'username',
				value : username,
				allowBlank : false,
				blankText : '<font color="red">姓名不能为空！</font>',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '性别',
				name : 'sex',
				value : sex,
				allowBlank : false,
				blankText : '<font color="red">性别不能为空！</font>',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '邮箱',
				name : 'email',
				value : email,
				allowBlank : false,
				blankText : '<font color="red">邮件不能为空!</font>',
				width : 200
			}, {
				xtype : "datefield",
				fieldLabel : '生日',
				name : 'birthday',
				value : birthday,
				allowBlank : false,
				format : "Y-m-d",// 日期的格式
				altFormats : "Y/m/d|Ymd",
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '薪水',
				name : 'salary',
				value : salary,
				allowBlank : false,
				blankText : '<font color="red">薪水不能为空!</font>',
				width : 200
			}, {
				xtype : 'textfield',
				fieldLabel : '余额',
				name : 'account',
				value : account,
				allowBlank : false,
				blankText : '<font color="red">余额不能为空!</font>',
				width : 200
			}, {
				xtype : 'textfield',
				name : 'id',
				value : id,
				hidden : true
			} ],
			buttons : [ {
				text : '修改',
				handler : function() {
					if (modifyForm.getForm().isValid()) {
						modifyForm.getForm().submit({
							waitMsg : '正在提交数据，请稍后......',
							success : function() {
								Ext.Msg.alert("记录更新成功！");
								Ext.getCmp("modifyWindow").close();
								store.reload();
							},
							failure : function() {
								Ext.Msg.alert('记录更新失败，请检查约束条件');
							}
						});
					}
				}
			} ]
		});
		// 定义修改窗体
		var modifyWindow = new Ext.Window({
			id : 'modifyWindow',
			title : '修改记录',
			layout : 'fit',
			width : 300,
			height : 260,
			iconCls : 'modifyIcon',
			modal : true,
			frame : true,
			plain : true,
			resizeable : false,
			items : modifyForm
		});

		modifyWindow.show();
	}
	// 查询人员信息的处理函数
	function searchPerson() {
		var value = Ext.getCmp('keyWord').getValue();
		store.load({
			params : {
				start : 0,
				limit : 8,
				username : value
			}
		});
	}
});