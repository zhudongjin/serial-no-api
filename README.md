## id自动生成序列 服务端（serial-api）
## serial-api
## - serial-facade -- 门面（第二/三方项目依赖工程）
## - serial-web -- 服务层

脚本：

CREATE TABLE `system_counter` (
`counter_id` varchar(64) DEFAULT NULL COMMENT '计数器ID',
`counter_name` varchar(64) DEFAULT NULL COMMENT '计数器名称',
`value` bigint(11) DEFAULT NULL COMMENT '当前值',
`max_value` bigint(11) DEFAULT NULL COMMENT '最大值',
`remark` varchar(128) DEFAULT NULL COMMENT '备注',
`create_user` int(11) DEFAULT NULL COMMENT '创建人',
`create_user_name` varchar(64) DEFAULT NULL COMMENT '创建人名',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
