
var menuItem=Vue.extend({
    name:'menu-item',
    props:{item:{}
    },
    template:[
        '<li>',
        '<a v-if="item.type===0" href="javascript;" >'
    ].join('')
});

var vm=new Vue({
    el:"#rrapp",
    data:{
        user:{},
        menuList:{},
        main:"mian.html",
        password:'',
        newPassword:'',
        navTitle:'控制台'
    },
    methods: {
        getMenuList: function (event) {
            $.getJSON("sys/menu/nav?_"+$.now(),function (r) {
                vm.menuList=r.menuList;
            });
        },
        getUser:function () {
            $.getJSON("sys/menu/info?_"+$.now(),function (r) {
                vm.user=r.user;
            });
        },
        created:function () {
            this.getMenuList();
            this.getUser();
        },
        updated:function () {
            var router=new Router();
            routerList(router,vm.menuList);
            router.start();
        }

    }

});
function routerList(router,menuList) {
    for (var key in menuList){
        var menu=menuList[key];
        if(menu.type==0){
            routerList(router,menu.list);
        }else if(menu.type==1){
           router.add('#'+menu.url,function () {
               var url=window.location.hash;
               vm.main=url.replace('#','');

               $(".treeview-menu li").removeClass("active");
               $("a[href='"+url+"']").parents("li").addClass("active");

               vm.navTitle = $("a[href='"+url+"']").text();
           })
        }
    }
}
