$package('IQB.orderQuery');
IQB.orderQuery = function(){
	var _grid = null;
	var _tree = null;
	var _this = {
		cache :{
			
		},
		config: {
			action: {//页面请求参数
				exports: urls['cfm'] + '/business/exportOrderList'
  			},
			event: {//按钮绑定函数，不定义的话采用默认
				update: function(){
					_grid.handler.update();
					$('#update-win-label').text('修改订单');
					$('#update-win #merchantNo').attr("disabled",true);
				}
			},
  			dataGrid: {//表格参数  				
	   			url: urls['cfm'] + '/business/getOrderList',
	   			queryParams: {
	   				type: 1
	   			}
			}
		},
		exports : function(){
			$("#btn-export").click(function(){
				var merchName = $("#merchName").val();
				var regId = $("#regId").val();
				var userName = $("#userName").val();
				var riskStatus = $("#riskStatus").val();
				var wfStatus = $("#wfStatus").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var datas = "?merchName=" + merchName + "&regId=" + regId + "&userName=" + userName + "&riskStatus=" + riskStatus + "&wfStatus=" + wfStatus + "&startTime=" + startTime + "&endTime=" + endTime;
	            var urls = _this.config.action.exports + datas;
	            $("#btn-export").attr("href",urls);
			});
		},
		init: function(){ 
			_grid = new DataGrid(_this.config); 
			_grid.init();//初始化按钮、表格
			_this.exports();//导出
		}
	}
	return _this;
}();

$(function(){
	//页面初始化
	IQB.orderQuery.init();
	datepicker(startTime);
	datepicker(endTime);
});	
/*显示日历*/ 
function datepicker(id){
	var date_ipt = $(id)
	$(id).datetimepicker({
	    lang:'ch',
	    timepicker:false,
	    format:'Y-m-d',
		allowBlank: true
	});
};
