$(function () {

    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/order/page',
        datatype: "json",
        colModel: [
            {label: '订单id', name: 'orderId', width: 75},
            {label: '部门名称', name: 'deptName', width: 75},
            {label: '订单描述', name: 'desc', width: 75},
            {label: '创建时间', name: 'createTime',index: "create_time", width: 90}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        //multiselect: true,
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
