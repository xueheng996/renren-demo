$(function () {

    $("#jqGrid").jqGrid({
        url: 'http://localhost:8088/renren-demo/' + 'sys/user/list',
        datatype: "json",
        colModel: [

            {label: '用户名', name: 'username', width: 75},

            {label: '邮箱', name: 'email', width: 90}

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
var setting = {
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
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null
        },
        showList: true,
        title: null,
        roleList: {},
        user: {
            status: 1,
            deptId: null,
            deptName: null,
            roleIdList: []
        }
    },
    methods: {
        add: function () {
            vm.showList = false;
            vm.title = "新增";

            vm.getDept();
        },
        reload: function () {
            vm.showList = true;
        },
        saveOrUpdate: function () {
            alert('部门名称=' + vm.user.deptName);
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