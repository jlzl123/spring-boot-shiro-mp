function handlerData(res) {
    return res.records;//records是Page对象的属性
}

function actionFormatter(value,row,index){
	var id=row.id;
	var res = '<button data-toggle="modal" class="btn btn-primary" href="list.html#modal-form" data-row='+id+' onclick="setFromData('+index+')">修改</button>';
	res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + id + '\')">删除</button>';
	return res;
}

function closeModal() {
    $('#modal-form').modal('hide');
    $('#modal-add-form').modal('hide');
}

//function setFromData(index){
//	var tdObj=$("tr[data-index="+index+"]").children().first();
//	$("#id").val($(tdObj).next().text());
//	$("#name").val($(tdObj).next().next().text());
//	$("#description").val($(tdObj).next().next().next().text());
//}
function setFromData(index) {
	var permData = new Array();
	permData = $.map($("tr[data-index=" + index + "]").children(), function(obj) {
		return $(obj).text();
	})
	$("#id").val(permData[1]);
	$("#name").val(permData[2]);
	$("#description").val(permData[3]);
}

function confirm(){
	var arr=new Array();
	arr=$.map($("input[name='btSelectItem']"),function(obj){
		if($(obj).is(":checked")){
			return $(obj).parent().next().text();
		}
	})
	if(arr.length>0){
		swal({
			title:"操作提示", //弹出框的title
			text:"确定删除所有选中角色吗",//弹出框里面的提示文本
			type:"warning",//弹出框类型
			showCancelButton:true,//是否显示取消按钮
			confirmButtonColor:"#DD6B55",//确定按钮颜色
			cancelButtonText:"取消",//取消按钮文本
			confirmButtonText:"是的，确定删除!",//确定按钮上面的文档
			closeOnConfirm:true
		},function(){
			$.ajax({
				type:"POST",
				url:"deleteList",
				data:{
					"roleIds":arr
				},
			    dataType:"json",
				traditional:true,//ajax提交数组
			    success:function(flag){
			    	if(flag){
			    		swal({
			    			icon:"success",
			    			title:"删除成功！"
			    		})
			    		$("#roleTable").bootstrapTable("refresh",{url:"/admin/role/list"});
			    	}else{
			    		swal({
			    			icon:"error",
			    			title:"删除失败！"
			    		})
			    	}
			    }
			})
		})
	}else{
		swal({
			title:"请选择需要删除的角色！",
			confirmButtonText:"确认"
		})
	}
	
}

function deleteOne(id){
	swal({
		title:"操作提示", //弹出框的title
		text:"确定删除该角色吗",//弹出框里面的提示文本
		type:"warning",//弹出框类型
		showCancelButton:true,//是否显示取消按钮
		confirmButtonColor:"#DD6B55",//确定按钮颜色
		cancelButtonText:"取消",//取消按钮文本
		confirmButtonText:"是的，确定删除!",//确定按钮上面的文档
		closeOnConfirm:true
	},function(){
		$.ajax({
			type:"POST",
			url:"deleteList",
			data:{
				"roleIds":[id]
			},
		    dataType:"json",
			traditional:true,//ajax提交数组
		    success:function(flag){
		    	if(flag){
		    		swal({
		    			icon:"success",
		    			title:"删除成功！"
		    		})
		    		$("#roleTable").bootstrapTable("refresh",{url:"/admin/role/list"});
		    	}else{
		    		swal({
		    			icon:"error",
		    			title:"删除失败！"
		    		})
		    	}
		    }
		})
	})
}