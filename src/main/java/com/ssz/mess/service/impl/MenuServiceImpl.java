package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.MenuMapper;
import com.ssz.mess.pojo.Menu;
import com.ssz.mess.pojo.MenuExample;
import com.ssz.mess.service.IMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    public boolean saveMenu(Menu menu) {
        if(menuMapper.getMenuById(menu.getId())==null){
            return false;
        }
        return menuMapper.insert(menu) > 0;
    }

    @Override
    @Transactional
    public Menu findMenuById(Integer id) {
        return menuMapper.getMenuById(id);
    }

    @Override
    @Transactional
    public List<Menu> getMenus() {
        return menuMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean updateMenu(Integer id, Menu menu) {
        Menu m = menuMapper.getMenuById(id);
        if(m == null){
            throw new RuntimeException("id不存在");
        }
        BeanUtils.copyProperties(menu,m);
        return menuMapper.updateByPrimaryKey(menu) > 0;
    }

    @Override
    @Transactional
    public void deleteMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public List<Menu> getMenusByStoreId(Integer id) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andStoreIdEqualTo(id);
        return menuMapper.selectByExample(menuExample);
    }
}
