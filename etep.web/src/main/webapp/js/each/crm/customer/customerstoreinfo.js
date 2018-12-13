$package('IQB.customerstoreinfo');
IQB.customerstoreinfo = function(){
	var _box = null;
    var _list = null;
    var imgUrl = '';
	var _this = {
		cache:{},
		config: {
			action: {
  				update: urls['sysmanegeUrl'] + '/customerStore/updateCustomerStoreInfo',
  				getById: urls['sysmanegeUrl'] + '/customerStore/getCustomerStoreInfoByCode',
  				getCustomerInfoByCustomerType: urls['sysmanegeUrl'] + '/customer/getCustomerInfoByCustomerType',
  			},
			event: {
				reset: function(){//重写save	
					_box.handler.reset();
					$('#query-belongsArea').val(null).trigger('change');
				},
				update: function(){//重写update
					_this.extFunc.update();
				},
			},
  			dataGrid: {
  				url: urls['cfm'] + '/customerStore/getCustomerStoreInfoList',
  				singleCheck: true
			}
		},
		extFunc:{
			checkCustomerStoreInfo: function(){
				var records = _box.util.getCheckedRows();
				if (_box.util.checkSelectOne(records)){
					var option = {};
			    	option['customerCode'] = records[0].customerCode;
			    	IQB.getById(_this.config.action.getById, option, function(result){		
			    		$("#updateForm")[0].reset(); 
			    		$("#updateForm").form('load',result.iqbResult.result);

			    		$('#select-guaranteeCorporationName').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			    		$('#select-creditorBankName').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			    		
			    		$("#update-win").modal({backdrop: 'static', keyboard: false, show: true});
					});
			    	$("input").attr("disabled",true);
			    	$("select").attr("disabled",true);
			    	$("#btn-save").css({ "display": "none" });
			    	$("#btn-close").click(function(){
			    		$("input").removeAttr("disabled");
			    		$("select").removeAttr("disabled");
			    		$("#btn-save").css({ "display": "initial" });
			    	});
				}
			},
			provinceSelect: function(){
				$("#select-city").empty();
				var val = '';
				if($("#select-province").val() == '北京市'){
					val = '[{"id":"请选择","text":"请选择"},{"id":"北京市","text":"北京市"}]';
					$('#select-city').select2({theme: 'bootstrap', data: JSON.parse(val)});
				}
				if($("#select-province").val() == '湖南省'){
					val = '[{"id":"请选择","text":"请选择"},{"id":"长沙市","text":"长沙市"}]';
					$('#select-city').select2({theme: 'bootstrap', data: JSON.parse(val)});
				}
			},
			initGuaranteeCorporationNameSelect: function() {
				IQB.post(urls['cfm'] + '/customer/getCustomerInfoByCustomerType', {customerType: 5}, function(result){
					var arry = [];
					var obj = {};
					obj.id = '';
					obj.text = '请选择';
					arry.push(obj);
					$.each(result.iqbResult.result, function(i, n){
						var obj = {};
						obj.id = n.customerName;
						obj.text = n.customerName;
						arry.push(obj);
					});
					$('#select-guaranteeCorporationName').select2({theme: 'bootstrap', data: arry});
					//缓存
					_this.cache.guaranteeCorporationName = result.iqbResult.result;
				});	
			},
			initGuaranteeCorporationNameIpt: function() {
				$('#select-guaranteeCorporationName').change(function(e){
					var target = e.target;
					$.each(_this.cache.guaranteeCorporationName, function(i, n){
						if($(target).val() == n.customerName){
							$('#guaranteeCorporationCorName').val(n.corporateName);
							$('#guaranteeCorporationCode').val(n.customerCode);
						}
					});
				});
			},
			formaterStatus: function(val) {
				if(val == '1'){
					return '已审核';
				}
				if(val == '2'){
					return '未审核';
				}
				return '未审核';
			},
			update: function(){
				var records = _box.util.getCheckedRows();
				if (_box.util.checkSelectOne(records)){
					var option = {};
			    	option['customerCode'] = records[0].customerCode;
			    	IQB.getById(_this.config.action.getById, option, function(result){	
			    		$("#updateForm")[0].reset();  
			    		$("#updateForm").form('load',result.iqbResult.result);
			    		
			    		$('#select-guaranteeCorporationName').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			    		$('#select-creditorBankName').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			    		
			    		$("#updateForm").prop('action',_this.config.action.update);
			    		$("#update-win").modal({backdrop: 'static', keyboard: false, show: true});
					});
					$("#btn-close").click(function(){
					});
				}
			}
		},
		init: function(){
			_box = new DataGrid2(_this.config); 
            _list = new Tree(_this.config); 
			_box.init();
			this.initSelect();
			this.initBtnClick();
			this.initLevelSelect();
			this.initOthers();
		},
		initSelect: function(){
			IQB.getDictListByDictType('query-province', 'CUSTOMER_PROVINCE');
			IQB.getDictListByDictType('query-belongsArea', 'BELONGS_AREA');
			IQB.getDictListByDictType('select-creditorBankName', 'BANK_NAME');
			$('#query-province').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			$('#query-belongsArea').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			$('#select-creditorBankName').select2({minimumResultsForSearch: 'Infinity', theme: 'bootstrap'});
			_this.extFunc.initGuaranteeCorporationNameSelect();	
			_this.extFunc.initGuaranteeCorporationNameIpt();	
		},
		initBtnClick: function(){
			$('#btn-check').click(_this.extFunc.checkCustomerStoreInfo);
		},
		initOthers: function(){
		},
		parseCustomerType : function(val, dictType) {
		},initLevelSelect: function(){
			$('#query-province').change(_this.extFunc.provinceSelect);
		}
	}
	return _this;
}();

$(function(){
	/** 初始化表格  **/
	IQB.customerstoreinfo.init();
});	


