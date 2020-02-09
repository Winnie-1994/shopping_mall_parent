 //控制层 
app.controller('typeTemplateController' ,function($log, $scope,$controller   ,typeTemplateService,brandService,specificationService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		typeTemplateService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){
		console.info("eject typeTemplate findPage");
		typeTemplateService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){
        console.info("typeTemplateController->findOne：query " + id);
		typeTemplateService.findOne(id).success(
			function(response){
				$scope.template= response;
				
				//转换字符串为json对象（集合）
				$scope.template.brandIds=  JSON.parse( $scope.template.brandIds);
				$scope.template.specIds= JSON.parse($scope.template.specIds);
				$scope.template.customAttributeItems = JSON.parse($scope.template.customAttributeItems);
				
			}
		);				
	}
	
	//保存 
	$scope.save=function(){
		console.info("typeTemplateController->save：" + angular.toJson($scope.template, true));
		var serviceObject;//服务层对象  				
		if($scope.template.id!=null){//如果有ID
			serviceObject=typeTemplateService.update( $scope.template ); //修改
		}else{
			serviceObject=typeTemplateService.add( $scope.template  );//增加
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.del=function(){
		//获取选中的复选框			
		typeTemplateService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		typeTemplateService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	$scope.brandList={data:[]};//品牌列表
    
	//读取品牌列表
	$scope.findBrandList=function(){
		brandService.selectOptionList().success(
			function(response){
				$scope.brandList={data:response};
			}
		);		
	}
	
	$scope.specList={data:[]};//规格列表
	
	//读取规格列表
	$scope.findSpecList=function(){
		specificationService.selectOptionList().success(
				function(response){
					$scope.specList={data:response};
				}
		);		
	}
	
	//增加扩展属性行
	$scope.addTableRow=function(){
		$scope.template.customAttributeItems.push({});
	}
	//删除扩展属性行
	$scope.delTableRow=function(index){
		$scope.template.customAttributeItems.splice( index,1);
	}
	
});	
