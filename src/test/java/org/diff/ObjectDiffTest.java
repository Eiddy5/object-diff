package org.diff;

import org.diff.entry.Company;
import org.diff.entry.User;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObjectDiffTest {
    private Object base;
    private Object working;


    @Test
    public void objectDiff() {
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(company1(), company2());
        List<Change> changes = diff.getChanges();
        changes.forEach(change -> {
            if (change instanceof ValueChange valueChange) {
                Object left = valueChange.getLeft();
                Object right = valueChange.getRight();
                if (left == null && right != null) {
                    System.out.println("add");
                } else if (left != null && right == null) {
                    System.out.println("remove");
                } else {
                    System.out.println("modified");
                }
                Optional<Object> affectedObject = valueChange.getAffectedObject();
                affectedObject.ifPresent(object -> {
                });
                String propertyName = valueChange.getPropertyName();

            }
        });

//        DiffNode node = ObjectDiffer.use().compare(company1(), company2());
//        System.out.println(node);
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

    private Company company2() {
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
