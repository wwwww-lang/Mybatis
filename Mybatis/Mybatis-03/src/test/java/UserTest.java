import com.wei.Dao.StudentMapper;
import com.wei.Dao.TeacherMapper;
import com.wei.pojo.Student;
import com.wei.pojo.Teacher;
import com.wei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTest {
    @Test
    public void test(){
//        SqlSession sqlSession= MybatisUtils.getSqlsession();
//        UserDaoMapper mapper=sqlSession.getMapper(UserDaoMapper.class);
////        List<User> users=mapper.getUsers();
////
////        for (User user:users
////             ) {
////            System.out.println(user);
////
////        }
//        User userById=mapper.getUserById(1);
//        System.out.println(userById);
//        sqlSession.close();

        SqlSession sqlsession = MybatisUtils.getSqlsession();
        TeacherMapper mapper = sqlsession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlsession.close();
    }

    @Test
    public void StudentTest(){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        StudentMapper mapper = sqlsession.getMapper(StudentMapper.class);
        List<Student> students=mapper.getStudent();

        for (Student student:students) {
            System.out.println(student);
        }
        sqlsession.close();
    }

}
