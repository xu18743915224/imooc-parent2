mini.parse();
//var grid = mini.get("userDatagrid");

//初始化数据
$(function () {
    init();
});

function init() {
    //加载grid
    //loadGrid();
    //var menu = new ColumnsMenu(grid);
}

function loadGrid() {
    //grid.setUrl(user_list)
    grid.load();
    /*$.ajax({
        type: "POST",
        url: user_list,
        data: {"pageNum":"1","pageSize":"20"},
        async: true,
        success: function (data) {
            alert(data);
            //var jsonObj=data.result.list;
            grid.setData(mini.decode(data));

        }
    });*/

}


function add() {
    mini.open({
        targetWindow: window,
        url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
        title: "新增员工", width: 600, height: 400,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = {action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {

            grid.reload();
        }
    });
}

function edit() {
    var row = grid.getSelected();
    if (row) {
        mini.open({
            url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
            title: "编辑员工", width: 600, height: 400,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "edit", id: row.id};
                iframe.contentWindow.SetData(data);

            },
            ondestroy: function (action) {
                //var iframe = this.getIFrameEl();
                grid.reload();
            }
        });

    } else {
        alert("请选中一条记录");
    }
}

function remove() {

    var rows = grid.getSelecteds();
    if (rows.length > 0) {
        if (confirm("确定删除选中记录？")) {
            var ids = [];
            for (var i = 0, l = rows.length; i < l; i++) {
                var r = rows[i];
                ids.push(r.id);
            }
            var id = ids.join(',');
            grid.loading("操作中，请稍后......");
            $.ajax({
                url: "../data/AjaxService.aspx?method=RemoveEmployees&id=" + id,
                success: function (text) {
                    grid.reload();
                },
                error: function () {
                }
            });
        }
    } else {
        alert("请选中一条记录");
    }
}

function search() {
    var key = mini.get("key").getValue();
    grid.load({key: key});
}

function onKeyEnter(e) {
    search();
}

/////////////////////////////////////////////////
function onBirthdayRenderer(e) {
    var value = e.value;
    if (value) return mini.formatDate(value, 'yyyy-MM-dd');
    return "";
}

function onMarriedRenderer(e) {
    if (e.value == 1) return "是";
    else return "否";
}

var Genders = [{id: 1, text: '男'}, {id: 2, text: '女'}];

function onGenderRenderer(e) {
    for (var i = 0, l = Genders.length; i < l; i++) {
        var g = Genders[i];
        if (g.id == e.value) return g.text;
    }
    return "";
}
