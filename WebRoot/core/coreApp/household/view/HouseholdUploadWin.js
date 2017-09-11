Ext.define('core.household.view.HouseholdUploadWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.householdUploadWin',
    //requires: ['Ext.form.Panel'],
    //height: 380,
   // width: 300,
    title: '文件上传',
    closeAction: 'hide',
    layout: 'fit',
    resizable: false,
    modal: true,
    items: {
    	xtype:"uploadpanel",
        header: false,
        addFileBtnText : '选择文件...',
        uploadBtnText : '上传',
        removeBtnText : '移除所有',
        cancelBtnText : '取消上传',
        file_size_limit : 100,//MB
        width : 750, 
        height : 300, 
        flash_url : "js/swfupload/swfupload.swf", 
        flash9_url : "js/swfupload/swfupload_fp9.swf",
        upload_url : 'UploadAction.action'
    }
});
