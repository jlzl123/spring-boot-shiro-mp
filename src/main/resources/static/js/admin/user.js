//为data-response-handler自定义属性提供表数据，为json格式
function handlerData(res) {
    return res.records;//records是Page对象的属性
}

//根据状态值格式化状态栏
function stateFormatter(value,row,index) {
    if (value == 1)
        return "启用";
    else
        return "禁用";
}

function sexFormatter(value,row,index){
	if(value==1){
		return "男";
	}else if(value==2){
		return "女";
	}else{
		return "未知";
	}
}

function actionFormatter(value,row,index){
	var state=row.state;
	var id=row.id;
	var res = '<button data-toggle="modal" class="btn btn-primary" href="list.html#modal-form" data-row='+id+' onclick="setFromData('+index+')">修改</button>';
	if(state==1){
		res += ' <button type="button" class="btn btn-default" onclick="changeState(\'' + id + '\',0,this)" >禁用</button>';
	}else{
		res += ' <button typ-e="button" class="btn btn-info" onclick="changeState(\'' + id + '\',1,this)" >启用</button>';
	}
	res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + id + '\')">删除</button>';
	return res;
}

function changeState(id,state,obj){
	$.ajax({
		type:"POST",
		url:"changeState",
		data:{
			id:id,
			state:state
		},
	    success:function(data){
//	    	var res;
//	    	var s;
//	    	if(state==1){
//	    		s="开启";
//	    		res = ' <button type="button" class="btn btn-default" onclick="changeState(\'' + id + '\',0,this)" >禁用</button>';
//	    	}else{
//	    		s="禁用";
//	    		res = ' <button typ-e="button" class="btn btn-info" onclick="changeState(\'' + id + '\',1,this)" >启用</button>';
//	    	}
//	    	$(obj).parent().prev().text(s);
//	    	$(obj).prev().after(res);
//	    	$(obj).remove();
	    	$('#userTable').bootstrapTable('refresh', {url: '/admin/user/list'});//刷新整个表的数据
	    },
		error:function(){
			alert("操作错误")
		}
	})
}

function setFromData(index){
	var tdObj=$("tr[data-index="+index+"]").children().first();
	var value=new Array();
	for(var i=0;i<5;i++){
		tdObj=$(tdObj).next();
		value[i]=$(tdObj).text();
	}
	$("#id").val(value[0]);
	$("#name").val(value[1]);
	$("#phone").val(value[3]);
	$("input[name='password']").val("000000");
	$("#remark").val(value[4]);
}

function deleteOne(id){
	layer.confirm('您确定要删除此行数据？', {
        btn: ['确定','取消'] //按钮
    },function(){
    	$.ajax({
    		type:"POST",
    		url:"deleteOne",
    		data:"id="+id,
    		success:function(status){
    			if(status==1){
    				layer.msg('删除成功', {icon: 1});
    				$('#userTable').bootstrapTable('refresh', {url: '/admin/user/list'});
    			}else{
    				layer.msg("删除失败",{icon:1});
    			}
    		}
    	})
    })
}

function closeModal() {
    $('#modal-form').modal('hide');
}
