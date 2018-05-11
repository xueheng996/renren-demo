$(function () {
    $("#jqGrid").jqGrid({
        url:baseURL+'sys/user/list',
        datatype:"json",
        colModel:[

        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: '#jqGridPager',
        jsonReader:{
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames:{
            page: "page",
            rows: "limit",
            order: "order"
        }
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    var setting={
        data:{
            simpleData:{
                enable: true,
                idKey: "deptId",
                pIdKey: "parentId",
                rootPId: -1
            },
            key:{
                url:"nourl"
            }
        }
    }
});