package org.diff;

import org.diff.entry.Company;
import org.diff.entry.User;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ObjectDiffTest {

    @Test
    public void objectDiff(){
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(company1(), company2());
        System.out.println(diff);
        DiffNode node = ObjectDiffer.use().compare(company1(), company2());
        System.out.println(node);
    }

    private Company company1() {
        User user = new User();
        user.id = "1";
        user.name = "李四";
        user.age = 18;

        Company company = new Company();
        company.id = "1";
        company.name = "test11";
        company.members = new ArrayList<>();
        company.members.add(user);
        return company;
    }

    private Company company2(){
        User user = new User();
        user.id = "1";
        user.name = "张三";
        user.age = 18;

        Company company = new Company();
        company.id = "1";
        company.name = "test";
        company.members = new ArrayList<>();
        company.members.add(user);
        return company;
    }
}
