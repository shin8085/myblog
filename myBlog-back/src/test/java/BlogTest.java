import com.shin.pojo.Blog;
import com.shin.service.Impl.BlogServiceImpl;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class BlogTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogServiceImpl blogServiceImpl = (BlogServiceImpl) context.getBean("blogServiceImpl");
        //int result=blogServiceImpl.addBlog(new Blog("171","shin","test","test",new Timestamp(System.currentTimeMillis())));
        //int result=blogServiceImpl.deleteBlogById("141");
        //Blog blog= blogServiceImpl.queryBlogById("171");
        //System.out.println(blog);
        List<Blog> blogs=blogServiceImpl.queryAllBlogs();
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }
}
