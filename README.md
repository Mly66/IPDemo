# IP地址查询系统

## 功能

- IP地址查询：输入IP地址，返回该IP的详细信息
- 端口号查询：根据端口号查询相关的IP地址信息
- 描述信息查询：根据关键词搜索IP地址的描述信息
- 支持多种查询方式：支持IP地址、端口号、描述信息三种查询方式
- 响应式界面：美观的用户界面，支持多种设备访问

## 数据库

IP地址表（ip_address）：
- id：主键
- ip_address：IP地址
- port：端口号
- description：描述信息
- created_at：创建时间

## 开发说明

1. IP地址查询
```
GET /api/ip/search?ipAddress={ipAddress}
```

2. 端口号查询
```
GET /api/ip/search/port?port={port}
```

3. 描述信息查询
```
GET /api/ip/search/description?keyword={keyword}
```
