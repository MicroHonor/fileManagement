Ext.define("core.innerCatalog.view.InnerCatalogLayout", {
			extend : 'Ext.panel.Panel',
			alias : 'widget.innerCataloglayout',
			title : "<center height=40>卷内目录输入</center>",
			closable : true,
			closeAction : 'destroy',
			defaults : {
				bodyStyle : 'padding:1px'
			},
			layout : 'fit',
			items:[{
				xtype:"innerCataloggrid"
			},{
				xtype:"innerCatalogComparedGrid",
				hidden:false
			}]
});