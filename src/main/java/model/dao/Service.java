package model.dao;

import model.dao.impl.AdminDao;
import model.dao.impl.RootDao;


public class Service {

    private Service(){ }
    private static class LazyHolder{
        public static final Service INSTANCE = new Service();
    }
    public static Service getInstance(){
        return Service.LazyHolder.INSTANCE;
    }

    public AdminService getAdminService(){
        return AdminDao.getInstance();
    }

    public RootService getRootService(){
        return RootDao.getInstance();
    }
}
