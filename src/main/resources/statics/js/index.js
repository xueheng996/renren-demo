
var menuItem=Vue.extend({
    name:'menu-item',
    props:{item:{}},
    template:[
        '<li>',
        '   <a v-if="item.type===0" href="javascript:;" >',
        '       <i v-if="item.icon!=null" :class="item.icon"></i>',
        '       <span>{{item.name}}</span>',
        '   </a>',
        '   <ul v-if="item.type===0" class="treeview-menu">',
        '       <menu-item :item="item" v-for="item"></menu-item>',
        '   </ul>',

        '   <a v-if="item.type===1  && item.parentId===0" :href="\'#\'+item.url" >',
        '       <i v-if="item.icon!=null" :class="item.icon"></i>',
        '       <span>{{item.name}}</span>',
        '   </a>',

        '   <a v-if="item.type===1 && item.parentId!=0" :href="\'#\'+item.url"><i v-if="item.icon!=null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i>{{item.name}}</a>',
        '</li>'
    ].join('')
});

$(window).on('resize',function () {

    var $content=$('.content');
    $content.height($(this).height-120);
    $content.find('iframe').each(function () {
       $(this).height($content.height());
    });
}).resize();

Vue.component('menuItem',menuItem);

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
