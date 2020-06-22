var basePath = "http://127.0.0.1:8881"


//-----------------------------------------------用户管理_user
//跳转到主页
var user_indexPage=basePath+"/static/frame/frame1/index.html";
//跳转到登录页
var user_loginPage=basePath+"/static/frame/frame1/pages/admin/login.html";
//跳转到提示超时页面
var user_timeOutPage=basePath+"/static/frame/frame1/pages/admin/no_token_login.html";
//登录请求
var user_login = basePath + "/console/user/login";
//分页查询
var user_list = basePath + "/console/user/getListPage";
//根据ID查询
var user_queryById = basePath + "/console/user/queryById";
//新增
var user_save = basePath + "/console/user/saveOrUpdate";
//删除
var user_delete = basePath + "/console/user/delete";

//-----------------------------------------------角色管理_role
//分页查询
var role_list = basePath + "/console/role/getListPage";
//根据ID查询
var role_queryById = basePath + "/console/role/queryById";
//新增
var role_save = basePath + "/console/role/saveOrUpdate";
//删除
var role_delete = basePath + "/console/role/delete";

//-----------------------------------------------权限管理_permission
//分页查询
var permission_list = basePath + "/console/permission/getListPage";
//根据ID查询
var permission_queryById = basePath + "/console/permission/queryById";
//新增
var permission_save = basePath + "/console/permission/saveOrUpdate";
//删除
var permission_delete = basePath + "/console/permission/delete";
//权限授权角色
var permission_permissionToRole = basePath + "/console/permission/permissionToRole";

//-----------------------------------------------字典管理_dict
//=====字典类型SysDictType
//分页查询
var dictType_list = basePath + "/console/dict/getListPageType";
//删除字典类型以及旗下的所有字典数据
var dictType_deleteType = basePath + "/console/dict/deleteType";
//新增or更新
var dictType_saveOrUpdate = basePath + "/console/dict/saveOrUpdateType";
//根据ID查询
var dictType_queryDictTypeById = basePath + "/console/dict/queryDictTypeById";

//=====字典数据SysDictData
//根据ID查询
var dictData_queryDictDataByType = basePath + "/console/dict/getSysDictDataByType/";
//分页查询
var dictData_list = basePath + "/console/dict/getListPageData";
//删除
var dictData_delete = basePath + "/console/dict/deleteData";
//新增or更新
var dictData_saveOrUpdate = basePath + "/console/dict/saveOrUpdateData";