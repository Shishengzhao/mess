package com.ssz.mess.service;

import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.vo.StoreVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IStoreService {

    /**
     * 验证店家的账号和密码
     * @param id
     * @param password
     * @return 如果验证成功，则返回店家对象
     */
    Store checkStore(Integer id, String password);

    /**
     * 关联其他表查询
     * @param id
     * @return
     */
    StoreVo getStoreWithOthers(Integer id);

    boolean saveStore(Store store);

    Store getStoreById(Integer id);

    Store getStoreByStoreId(Integer id);

    List<Store> getStores();

    boolean updateStore(Integer id, Store store);

    void deleteStore(Integer id);

}
