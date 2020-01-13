package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.CommentMapper;
import com.ssz.mess.mapper.GradeMapper;
import com.ssz.mess.mapper.MenuMapper;
import com.ssz.mess.mapper.StoreMapper;
import com.ssz.mess.pojo.*;
import com.ssz.mess.pojo.vo.StoreVo;
import com.ssz.mess.service.ICommentService;
import com.ssz.mess.service.IMenuService;
import com.ssz.mess.service.IStoreService;
import com.ssz.mess.service.IUserService;
import com.ssz.mess.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public Store checkStore(Integer id, String password) {
        String pwd = MD5Utils.code(password);
        StoreExample storeExample = new StoreExample();
        StoreExample.Criteria criteria = storeExample.createCriteria();
        criteria.andIdEqualTo(id)
                .andPasswordEqualTo(pwd);
        List<Store> stores = storeMapper.selectByExample(storeExample);
        if(stores.size()==0){
            return null;
        }else {
            return stores.get(0);
        }
    }

    /**
     * 和其他表关联查询
     * @param id
     * @return
     */
    @Override
    @Transactional
    public StoreVo getStoreWithOthers(Integer id) {
        Store store = storeMapper.getStoreById(id);
        if(store == null){
            //说明不存在
            System.out.println("store不存在...");
            return null;
        }
        Integer storeId = store.getStoreId();
        Grade grade = gradeMapper.getGradeById(store.getGradeId());
        List<Menu> menus = menuService.getMenusByStoreId(storeId);
        List<Comment> comments = commentService.getCommentsByStoreId(storeId);
        StoreVo storeVo = new StoreVo();
        storeVo.setStore(store);
        storeVo.setGrade(grade);
        storeVo.setMenus(menus);
        storeVo.setComments(comments);
        return storeVo;
    }

    @Override
    @Transactional
    public boolean saveStore(Store store) {
        if(storeMapper.getStoreById(store.getId())!=null){
            return false;
        }else{
            store.setPassword(MD5Utils.code(store.getPassword()));
            //添加到用户表
            User user = new User();
            user.setId(store.getId());
            user.setPassword(store.getPassword());
            user.setRole(store.getRole());
            userService.saveUser(user);

            return storeMapper.insert(store) > 0;
        }
    }

    @Override
    @Transactional
    public Store getStoreById(Integer id) {
        return storeMapper.getStoreById(id);
    }

    @Override
    @Transactional
    public List<Store> getStores() {
        return storeMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean updateStore(Integer id, Store store) {
        Store s = storeMapper.getStoreById(id);
        if(s == null){
            throw new RuntimeException("id不存在");
        }

        //对store密码加密
        store.setPassword(MD5Utils.code(store.getPassword()));

        if(!store.getPassword().equals(s.getPassword())){
            //跟新user表
            User user = userService.getUserById(id);
            user.setPassword(store.getPassword());
            userService.updateUser(id, user);
        }

        BeanUtils.copyProperties(store,s);
        return storeMapper.updateByPrimaryKey(s) > 0;
    }

    @Override
    @Transactional
    public void deleteStore(Integer id) {
        //同步删除user表
        userService.deleteUser(id);

        storeMapper.deleteByPrimaryKey(id);
    }
}
