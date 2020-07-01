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
//根据角色ID获取(用户角色表数据)
var role_queryUserRoleByRoleId= basePath + "/console/role/queryUserRoleByRoleId/";
//根据角色ID获取(用户未授权数据)
var role_queryNoAuthUserByRoleId= basePath + "/console/role/queryNoAuthUserByRoleId/";
//角色授权用户
var role_roleToUser = basePath + "/console/role/roleToUser";
//查询角色列表
var role_queryRoleList= basePath + "/console/role/queryRoleList";

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
//根据权限ID获取(角色权限表数据)
var permission_queryRolePermissByPermissId= basePath + "/console/permission/queryRolePermissByPermissId/";
//根据权限ID获取(角色未授权数据)
var permission_queryNoAuthRoleByPermissId= basePath + "/console/permission/queryNoAuthRoleByPermissId/";

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

//-----------------------------------------------------------------------------------------------菜单管理
//分页查询
var menu_list = basePath + "/console/menu/getListPage";
//新增
var menu_save = basePath + "/console/menu/saveOrUpdate";
//根据ID查询
var menu_queryById = basePath + "/console/menu/queryById/";
//删除
var menu_delete = basePath + "/console/menu/delete/";
//根据ID查询树表格
var menu_getListById = basePath + "/console/menu/getGridListById/";
//菜单授权角色
var menu_menuToRole = basePath + "/console/menu/menuToRole";
//根据角色ID查询角色菜单表数据
var menu_queryMenuToRoleByRoleId = basePath + "/console/menu/queryMenuToRoleByRoleId/";
//根据用户ID查询用户所拥有的菜单列表
var menu_getIndexMenuTreeByUserId = basePath + "/console/menu/getIndexMenuTreeByUserId";