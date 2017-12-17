package test;

import com.apache.calcite.sql.rule.SQLRule;

import java.util.Map;

/**
 * Created by hanningning on 17/12/15.
 */
public class SQLRuleTest {

    public static void main(String[] args) {

        /**
         * 首次建链比较慢，实例化的过程中初始化了连接池，类似JDBC的连接池
         * 这个过程需要在程序初始化的时候就要准备好，不影响后续的规则执行
         * */
        SQLRule sqlRule = SQLRule.getInstance();
        sqlRule.addTable(new Student(1,20,"hanningning"));

        /**
         * 规则 demo1
         * */
        String ruleSql = "select count(*) as num from student where id = 2";
        Map<String,Object> map1 = sqlRule.execute(ruleSql);
        System.out.println(map1);

        /**
         * 规则 demo2
         * */
        String ruleSql2 = "select * from student where age>30";
        Map<String,Object> map2 = sqlRule.execute(ruleSql2);
        System.out.println(map2);

        /**
         * 规则 demo3
         * */
        String ruleSql3 = "select * from student where age<30";
        Map<String,Object> map3 = sqlRule.execute(ruleSql3);
        System.out.println(map3);


    }
}
