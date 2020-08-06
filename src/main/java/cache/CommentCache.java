package cache;

public class CommentCache {

    private CommentCache(){
    };
    private static class LazyHolder{
        public static final CommentCache INSTANCE = new CommentCache();
    }
    public static CommentCache getInstance(){
        return CommentCache.LazyHolder.INSTANCE;
    }
}
