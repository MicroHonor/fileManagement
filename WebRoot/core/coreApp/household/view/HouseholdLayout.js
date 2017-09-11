Ext.define("core.household.view.HouseholdLayout", {
			extend : 'Ext.panel.Panel',
			alias : 'widget.householdlayout',
			title : "<center height=40>户籍档案输入</center>",
			closable : true,
			closeAction : 'destroy',
			defaults : {
				bodyStyle : 'padding:1px'
			},
			layout : 'fit',
			items:[{
				xtype:"household_grid"
			},{
//				xtype:"householdform",
//				hidden:true
//			},{
				xtype:"householdComparedGrid",
				hidden:false
			}]
});