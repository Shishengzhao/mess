package com.ssz.mess.service;

import com.ssz.mess.pojo.Menu;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface IMenuService {

    boolean saveMenu(Menu menu);

    Menu findMenuById(Integer id);

    List<Menu> getMenus();

    boolean updateMenu(Integer id, Menu menu);

    void deleteMenu(Integer id);

    /**
     * 根据storeId查询菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByStoreId(Integer id);
}
