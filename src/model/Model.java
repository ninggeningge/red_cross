package model;

import java.sql.Connection;

/**
 * Created by ningge on 15/12/31.
 */

//所有数据表的父类
abstract public class Model {

    //数据库链接参数
    protected Connection connection;

    //插入一条数据
    abstract boolean insert();

    //更新数据
    abstract boolean update();

    //删除数据
    abstract boolean delete();

}
