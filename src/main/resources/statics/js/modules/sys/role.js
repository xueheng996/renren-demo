$(function () {

    $("#jqGrid").jqGrid({
        url: 'http://localhost:8088/renren-demo/' + 'sys/role/list',
        datatype: "json",
        colModel: [
            {label: '角色ID', name: 'roleId',index:"role_id", width: 45,key:true},
            {label: '角色名称', name: 'roleName', index:"role_name",width: 75},
            {label: '所属部门', name: 'deptName', width: 75,sortable:false},
            {label: '备注', name: 'remark', width: 100},
            {label:'创建时间', name:'createTime',index:'create_time',width:80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});
//菜单树
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        },
        check:{
            enable:true,
            nocheckInherit:true
        }
    }
};
//部门结构树
var dept_ztree;
var dept_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};

//数据树
var data_ztree;
var data_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        },
        check:{
            enable:true,
            nocheckInherit:true,
            cheboxType:{ "Y":"", "N" : ""}
        }
    }
};

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            roleName: null
        },
        showList: true,
        title: null,
        role: {
            deptId: null,
            deptName: null
        }
    },
    methods: {
        query:function(){
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.roleList={};
            vm.role={deptName:null, deptId:null};
            vm.getMenuTree(null);

            vm.getDept();

            vm.getDataTree();

        },
        saveOrUpdate:function(){
            var nodes=menu_ztree.getCheckedNodes(true);
            var menuIdList=new Array();
            for (var i=0;i<nodes.length;i++){
                menuIdList.push(nodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;

            var nodes=data_ztree.getCheckedNodes(true);
            var deptIdList=new Array();
            for (var i=0;i<nodes.length;i++){
                deptIdList.push(nodes[i].deptId);
            }
            vm.role.deptIdList = deptIdList;

            var url = vm.role.roleId == null ? "sys/role/save" : "sys/role/update";
            $.ajax({
                type:"POST",
                url:baseURL+url,
                contentType:"application/json",
                data:JSON.stringify(vm.user),
                success:function (r) {
                    if(r.code===0){
                        alert('操作成功',function () {
                            vm.reload();
                        })
                    }else{
                        alert(r.msg);
                    }
                }
            });

        },
        update:function(){
            var roleId=getSelectedRow();
            if(roleId==null){
                return;
            }
            vm.showList=false;
            vm.title="修改";

            vm.getDataTree();
            vm.getMenuTree(roleId);
            vm.getDept();
        },
        getUser: function(userId){
            $.get(baseURL+"sys/user/info/"+userId, function (r) {
                vm.user=r.user;
                vm.user.password=null;

                vm.getDept();
            });
        },
        del:function(){
            var roleIds=getSelectedRows();
            if(roleIds==null){
                return;
            }
            confirm('确认要删除选中的记录?',function () {
                $.ajax({
                    type: "POST",
                    url: baseURL+"sys/role/delete",
                    contentType:"application/json",
                    data: JSON.stringify(roleIds),
                    success:function (r) {
                        if(r.code==0){
                            alert('操作成功',function () {
                                vm.reload();
                            });
                        }else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        reload: function () {
            vm.showList = true;
            var page=$('#jqGrid').jqGrid('getGridParam','page');
            $('#jqGrid').jqGrid('setGridParam',{
                postData:{'roleName':vm.q.username},
                page:page
            }).trigger("reloadGrid");
        },
        getMenuTree:function(roleId){
            //加载菜单树
            $.get(baseURL+'sys/menu/list',function (r) {
                menu_ztree=$.fn.zTree.init($("#menuTree"),menu_setting,r);
                menu_ztree.expandAll(true);
                if(roleId!=null){
                    vm.getRole(roleId);
                }
            });
        },
        getDataTree: function(roleId){

            $.get(baseURL+"sys/dept/list",function (r) {
                data_ztree=$.fn.zTree.init($("#dataTree"),data_setting,r);
                data_ztree.expandAll(true);
            });
        },
        getRole:function(roleId){
            $.get(baseURL+'sys/role/info'+roleId,function (r) {
                vm.role=r.role;

                var menuIds = vm.role.menuIdList;
                for (var i=0;i<menuIds.length;i++){
                    var node=menu_ztree.getNodeByParam("menuId",menuIds[i]);
                    menu_ztree.checkNode(node,true,false);
                }

                var deptIds=vm.role.deptIdList;
                for (var i=0;i<deptIds.length;i++){
                    var node=data_ztree.getNodeByParam("deptId",deptIds[i]);
                    data_ztree.checkNode(node,true,false);
                }
                vm.getDept();
            });
        },
        getDept: function () {
            //加载部门树
            $.get('http://localhost:8088/renren-demo/sys/dept/list', function (r) {
                ztree = $.fn.zTree.init($('#deptTree'), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if (node != null) {
                    ztree.selectNode(node);
                    vm.user.deptName = node.name;
                }
            })
        },

        getRoleList:function(){
            $.get(baseURL+"sys/role/select",function (r) {
                vm.roleList=r.list;
            });
        },
        deptTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: '',
                title: '选择部门',
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        }

    }
});