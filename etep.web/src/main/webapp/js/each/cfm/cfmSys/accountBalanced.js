$package('IQB.accountBalanced');
IQB.accountBalanced = function(){
	var _grid = null;
	var _tree = null;
	var _this = {
		cache :{
			
		},
		config: {
			action: {//页面请求参数
				refund: urls['cfm'] + '/back/refund',
				verifyPayment:urls['cfm'] + '/pay/verifyPayment'
  			},
			event: {//按钮绑定函数，不定义的话采用默认

			},
  			dataGrid: {//表格参数  				
	   			url: urls['cfm'] + '/credit/repayment/All',
	   			singleCheck: true,
	   			queryParams: {
	   				type: 1
	   			}
			}
		},
		getNowFormatDate : function() {
	          var date = new Date();
	          var seperator1 = "-";
	          var year = date.getFullYear();
	          var month = date.getMonth() + 1;
	          var strDate = date.getDate();
	          if (month >= 1 && month <= 9) {
	              month = "0" + month;
	          }
	          if (strDate >= 0 && strDate <= 9) {
	              strDate = "0" + strDate;
	          }
	          var currentdate = year + seperator1 + month + seperator1 + strDate;
	          return currentdate;
	    },
		accountBalance : function(){
			$("#btn-accountBalanced").click(function(){
				var rows = $("#datagrid").datagrid2('getCheckedRows');
				var orderId = rows[0].orderId;
				var curRepayAmt = rows[0].curRepayAmt;
				if(rows.length > 0){
					IQB.get(_this.config.action.verifyPayment, {'orderId':orderId}, function(result) {
					      if(result.retCode == 'success'){
				    	      $('#open-win').modal('show');
				    	      //赋值
						      $("#repayDate").val(_this.getNowFormatDate());
						      $("#curRepayAmt").val(curRepayAmt);
						      $("#tradeNo").val('');
						      $("#reason").val('');
							  $("#btn-save").click(function(){
								  if($("#updateForm").form('validate')){
									  var option = {
											  'orderId':orderId,
											  'repayDate':$('#repayDate').val(),
											  'tradeNo':$("#tradeNo").val(),
											  'reason':$("#reason").val()
									  };
									  IQB.get(_this.config.action.refund, option, function(result) {
										  if(result.iqbResult.result.retCode == 'success'){
											  IQB.alert('平账成功');
											  $('#open-win').modal('hide');
										  }else{
											  IQB.alert('平账失败');
											  $('#open-win').modal('hide');
										  }
									  })
								  }
							  });
		                      $("#btn-close").click(function(){
		                    	 $('#open-win').modal('hide');
							  });
					      }else if(result.retCode == 'error'){
					    	     IQB.alert(result.retMsg);
					      }
					})	
				}else{ 
					IQB.alert('未选中行');
				}
			});
		},
		init: function(){ 
			_grid = new DataGrid2(_this.config); 
			_grid.init();//初始化按钮、表格
			_this.accountBalance();
			/*_this.getNowFormatDate();*/
		}
	}
	return _this;
}();

$(function(){
	//页面初始化
	IQB.accountBalanced.init();
	datepicker(startDate);
	datepicker(endDate);
	datepicker(repayDate);
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
